package com.mummoom.md.ui.main.community

import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
//import com.google.firebase.storage.FirebaseStorage
import com.mummoom.md.R
import com.mummoom.md.data.Post.SendPost
import com.mummoom.md.data.remote.Post.PostService
import com.mummoom.md.data.remote.Post.PostView
import com.mummoom.md.databinding.ActivityWriteBinding
import com.mummoom.md.ui.BaseActivity

class WriteActivity : BaseActivity<ActivityWriteBinding>(ActivityWriteBinding::inflate), PostView {

    private var content : String = ""
    private var imgUrl : String = ""
    private var title : String = ""
//    private lateinit var newPost: SendPost

    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent())
    {
//        it -> // 받아온 url로 writeActivity 화면을 change 해주기?

        if(it == null)
        {
            imgUrl = ""
        }
        else
        {
            imgUrl = it.toString()
        }

        if(imgUrl.isNotEmpty())
        {
            Glide.with(this)
                .load(imgUrl)
                .into(findViewById(R.id.write_galleryBtn_iv))
        }

    }

    override fun initAfterBinding() {

        val plusImage = PlusImageCustomDialog(this)

        // 앱에서 앨범 접근을 허용할지 선택하는 메세지 (한번 허용하면 앱 설치되어있는 동안 안 뜸.)
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        // 뒤로가기 눌렀을 때
        binding.writeArrowIv.setOnClickListener {
            finish()
        }

        // 이미지 추가 버튼 눌렀을 때
        binding.writeGalleryBtnIv.setOnClickListener {
            plusImage.MyDig()
        }

        // 업로드 버튼 눌렀을 때
        binding.writeUploadBtnTv.setOnClickListener {
            uploading()
            finish()
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

            override fun onVideoClicked() {

            }

        })

    }

    private fun uploading()
    {
        content = binding.writeContentEt.text.toString()
        title = binding.writeTitleEt.text.toString()

        val newPost = SendPost(content, imgUrl, title)
        val savePostService = PostService()

        savePostService.setPostView(this)
        savePostService.posting(newPost)
    }



    // Firebase Storage에 이미지를 업로드 하는 함수
//    private fun uploadImageToFirebase(uri: Uri)
//    {
//        val storage : FirebaseStorage? = FirebaseStorage.getInstance()
//
//        var fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss").format(Date())}_.png"
//        var imagesRef = storage!!.reference.child("images/").child(fileName)
//
//        imagesRef.putFile(uri!!).addOnSuccessListener {
//            Toast.makeText(this, "업로드가 완료되었습니다.", Toast.LENGTH_LONG).show()
//        }.addOnFailureListener{
//            Toast.makeText(this, "업로드를 실패하였습니다.", Toast.LENGTH_LONG).show()
//        }
//    }

    override fun onPostLoading() {
        binding.writeUploadFrm.visibility = View.GONE
    }

    override fun onPostSuccess(data: Int) {
        binding.writeUploadFrm.visibility = View.VISIBLE
        Toast.makeText(this, "업로드가 완료되었습니다.", Toast.LENGTH_LONG).show()
        Log.d("post_upload", data.toString())
    }

    override fun onPostFailure(code: Int, message: String) {
        binding.writeUploadFrm.visibility = View.GONE
        Toast.makeText(this, "업로드가 실패하였습니다.", Toast.LENGTH_LONG).show()
    }

}