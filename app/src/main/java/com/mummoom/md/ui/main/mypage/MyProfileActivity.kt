package com.mummoom.md.ui.main.mypage

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.mummoom.md.R
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.User.UserService
import com.mummoom.md.databinding.ActivityMyprofileBinding
import com.mummoom.md.ui.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

class MyProfileActivity : BaseActivity<ActivityMyprofileBinding>(ActivityMyprofileBinding::inflate),MyprofileView,ChangeImgView ,ChangenameView{

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient : GoogleSignInClient

    private var uri : Uri? = null

    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent())
    {
        uri = it

        Glide.with(this)
            .load(it)
            .into(binding.myprofileProfileImgIv)
    }

    override fun initAfterBinding() {

        val changeImageDialog = ChangeImageCustomDialog(this)

        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        // 이미지 추가/변경 버튼 눌렀을 때
        binding.myprofilePlusBtnIv.setOnClickListener {
            changeImageDialog.MyDig()
        }

        // 비밀번호 edittext 부분 눌렀을 때

        binding.myprofilePwdTv.setOnClickListener{
            val intent = Intent(this, ChangePwdActivity::class.java)
            startActivity(intent)
        }

        binding.myprofilePwdEt.setOnClickListener {
            val intent = Intent(this, ChangePwdActivity::class.java)
            startActivity(intent)
        }

        // 뒤로가기 눌렀을 때
        binding.myprofileBackBtnIv.setOnClickListener {

            if(uri != null)  // 이 부분은 이미지를 변경했을 때 진입하는 부분
            {
                uploadImageToFirebase(uri!!)
                finish()
            }
            else  // 이미지 변경 안했을 때
            {
                finish()
            }

//            changeUsername()
        }

        // 회원탈퇴 눌렀을 때
        binding.myprofileWithdrawTv.setOnClickListener {
            //deleteUser()
//            Toast.makeText(this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show()
//            finish()

            startActivityWithClear(WithdrawActivity::class.java)
        }


        changeImageDialog.setOnClickedListener(object : ChangeImageCustomDialog.clickListener{
            override fun onPictureClicked() {
                if(ContextCompat.checkSelfPermission(this@MyProfileActivity.applicationContext,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    launcher.launch("image/*")
                }
                else
                {
                    Toast.makeText(this@MyProfileActivity, "갤러리 접근 권한이 거부되어 있습니다. 설정에서 접근을 허용해주십시오.",
                        Toast.LENGTH_LONG).show()
                }

            }

            override fun onIllustClicked() {
                startActivityWithClear(IllustrationActivity::class.java)
            }

        })
    }

    override fun onStart() {
        super.onStart()
        UserService.getUserByIdx(this)

    }

    // Firebase Storage에 이미지를 업로드 하는 함수
    private fun uploadImageToFirebase(uri: Uri)
    {
        val storage : FirebaseStorage? = FirebaseStorage.getInstance()

        var fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss").format(Date())}_.png"
        var imagesRef = storage!!.reference.child("images/").child(fileName)

        imagesRef.putFile(uri!!).addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                changeUserImg(it.toString())
            }
        }.addOnFailureListener{
            // 이미지 업로드 실패
        }
    }

    private fun deleteUser()
    {
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSingInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        auth.signOut()
        googleSingInClient?.signOut()

        user?.delete()
        googleSingInClient.revokeAccess()
    }

    // user 정보를 렌더링 해주는 함수
    fun getUser(user: User) {

        if(user.imgUrl != "" || user.imgUrl != null)
        {
            Glide.with(this)
                .load(user.imgUrl)
                .into(binding.myprofileProfileImgIv)
        }
        else
        {
            Glide.with(this)
                .load(R.drawable.ic_no_img2)
                .into(binding.myprofileProfileImgIv)
        }
        Log.d("user", user.toString())
        binding.myprofileEmailContentTv.setText(user.email)
        binding.myprofileNameContentTv.setText(user.nickName)
    }

    fun changeUserImg(newImgUrl : String)
    {
        val imgUrl = newImgUrl
        val user = User("", imgUrl, "", "", "", "")

        Log.d("imgUrl", imgUrl)
        UserService.changeUserImg(this, user)
    }

    fun changeUsername() {

        if (binding.myprofileNameContentTv.text.toString().isEmpty()) {
            Toast.makeText(this, "이름를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val nickname = binding.myprofileNameContentTv.text.toString()

        Log.d("NICKNAME",nickname)
        val user = User("","", nickname,"","","")

        UserService.changeUsername(this, user)
    }

    // user 정보 얻어와서 세팅하는 API
    override fun onMyprofileLoading() {

    }

    override fun onMyprofileSuccess(user: User) {
        getUser(user)
    }

    override fun onMyprofileFailure(code: Int, message: String) {

    }


    override fun onChangeprofileLoading() {

    }

    override fun onChangeprofileSuccess() {
        finish()
    }

    override fun onChangeprofileFailure(code: Int, message: String) {

    }


    override fun onChangenameLoading() {

    }

    override fun onChangenameSuccess() {
        Toast.makeText(this, "이름를 성공적으로 변경되었습니다.", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onChangenameFailure(code: Int, message: String) {
        when(code) {
            7002,7011,7016-> {
                binding.myprofileNameErrorTv.visibility= View.VISIBLE
                binding.myprofileNameErrorTv.text=message
            }

        }

    }


}