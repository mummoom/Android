package com.mummoom.md.ui.main.community

import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.FirebaseStorage
import com.mummoom.md.R
import com.mummoom.md.data.Post.SendPost
import com.mummoom.md.data.remote.Post.PostService
import com.mummoom.md.data.remote.Post.PostView
import com.mummoom.md.databinding.ActivityWriteBinding
import com.mummoom.md.ui.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

class WriteActivity : BaseActivity<ActivityWriteBinding>(ActivityWriteBinding::inflate), PostView {

    private var content : String = ""
    private var title : String = ""
    private var uri: Uri? = null
    private var imgUrl : String = ""

    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent())
    {
        uri = it

        Glide.with(this)
            .load(uri)
            .into(binding.writeGalleryBtnIv)
    }

    override fun initAfterBinding() {

        val plusImage = PlusImageCustomDialog(this)

        // 앱에서 앨범 접근을 허용할지 선택하는 메세지 (한번 허용하면 앱 설치되어있는 동안 안 뜸.) => if문으로 한번만 뜨게 수정해야 함
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        // 뒤로가기 눌렀을 때
        binding.writeArrowIv.setOnClickListener {
            finish()
        }

        // 갤러리 버튼 눌렀을 때
        binding.writeGalleryBtnIv.setOnClickListener {
            plusImage.MyDig()
        }

        // 업로드 버튼 눌렀을 때
        binding.writeUploadBtnTv.setOnClickListener {

            val animation = AnimationUtils.loadAnimation(this,R.anim.rotate)
            binding.writeRotateIv.visibility = View.VISIBLE
            binding.writeLoadingIv.visibility = View.VISIBLE
            binding.writeRotateIv.startAnimation(animation)

            if(binding.writeContentEt.text.toString().isEmpty() || binding.writeTitleEt.text.toString().isEmpty())
            {
                showToast("제목이나 내용이 없습니다.")
                finish()
                return@setOnClickListener
            }

            if(uri != null)
            {
                uploadImageToFirebase(uri!!)
            }
            else
            {
                uploading(this.imgUrl)
            }
        }

        plusImage.setOnClickedListener(object : PlusImageCustomDialog.clickListener{
            override fun onPictureClicked() {
                if(ContextCompat.checkSelfPermission(this@WriteActivity.applicationContext,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    launcher.launch("image/*")
                }
                else
                {
                    Toast.makeText(this@WriteActivity, "갤러리 접근 권한이 거부되어 있습니다. 설정에서 접근을 허용해주십시오.",
                        Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    // post를 작성하는 API 부분
    private fun uploading(imgUrl : String)
    {
        content = binding.writeContentEt.text.toString()
        title = binding.writeTitleEt.text.toString()
        this.imgUrl = imgUrl

        val newPost = SendPost(content, this.imgUrl, title)
        val savePostService = PostService()

        savePostService.setPostView(this)
        savePostService.posting(newPost)
    }


    // Firebase Storage에 이미지를 업로드 하는 함수
    private fun uploadImageToFirebase(uri: Uri)
    {
        val storage : FirebaseStorage? = FirebaseStorage.getInstance()

        var fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss").format(Date())}_.png"
        var imagesRef = storage!!.reference.child("images/").child(fileName)

        imagesRef.putFile(uri!!).addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                uploading(it.toString())
            }
        }.addOnFailureListener{
            // 이미지 업로드 실패
            showToast("업로드를 실패하였습니다.")
        }
    }

    // writePost API 부분
    override fun onPostLoading() {

    }

    override fun onPostSuccess(data: Int) {
        binding.writeRotateIv.animation.cancel()
        binding.writeRotateIv.visibility = View.GONE
        binding.writeLoadingIv.visibility = View.GONE

        showToast("업로드가 완료되었습니다.")
        Log.d("post_upload", data.toString())
        finish()
    }

    override fun onPostFailure(code: Int, message: String) {
        binding.writeRotateIv.animation.cancel()
        binding.writeRotateIv.visibility = View.GONE
        binding.writeLoadingIv.visibility = View.GONE

        showToast("업로드를 실패하였습니다.")
        finish()
    }

}