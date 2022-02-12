package com.mummoom.md.ui.main.mypage

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.mummoom.md.ApplicationClass
import com.mummoom.md.ApplicationClass.Companion.X_AUTH_TOKEN
import com.mummoom.md.R
import com.mummoom.md.data.entities.Dog
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.Dog.DogService
import com.mummoom.md.data.remote.User.UserService
import com.mummoom.md.databinding.FragmentMypageBinding
import com.mummoom.md.ui.BaseFragment
import com.mummoom.md.ui.doggender.DogInfoView
import com.mummoom.md.ui.doginfocheck.DogInfoCheckActivity
import com.mummoom.md.ui.login.LoginActivity
import com.mummoom.md.ui.main.community.MypageCustomDialog
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) ,MypageView,DogInfoView,MypageDogChangeView,MyprofileView,ChangeImgView{

    private lateinit var dogRVdadapter : DogprofileRVAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient : GoogleSignInClient

    var dogIdx :Int = 0
    private var uri : Uri? = null

    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent())
    {
        uri = it

        uploadImageToFirebase(uri!!)
    }

    override fun initAfterBinding() {

        // 다이얼로그 변수
        val plusDialog = MypageCustomDialog(requireContext())
        val modifyDialog = ModifyProfileCustomDialog(requireContext())
        val changeImageDialog = ChangeImageCustomDialog(requireContext())

        // 강아지 프로필 추가
        binding.mypageDogprofilePlusIv.setOnClickListener {
            plusDialog.MyDig()
        }

        // 내 프로필 사진 변경
        binding.mypageMysettingIv.setOnClickListener {
            changeImageDialog.MyDig()
        }

        changeImageDialog.setOnClickedListener(object : ChangeImageCustomDialog.clickListener{
            override fun onPictureClicked() {
                if(ContextCompat.checkSelfPermission(this@MypageFragment.requireActivity(),
                        android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    launcher.launch("image/*")
                }
                else
                {
                    Toast.makeText(requireContext(), "갤러리 접근 권한이 거부되어 있습니다. 설정에서 접근을 허용해주십시오.",
                        Toast.LENGTH_LONG).show()
                }

            }

            override fun onIllustClicked() {
                val intent = Intent(requireContext(), IllustrationActivity::class.java)
                startActivity(intent)
            }

        })



        // 강아지 프로필 수정
//        binding.mypageMoreBtnIv.setOnClickListener {
//            val items = getResources().getStringArray(R.array.year)

//        }


        // 확인 버튼 눌렀을 때 처리 코드들
        plusDialog.setOnClickedListener(object : MypageCustomDialog.TextClickListener{
            override fun onClicked(
                dogName : String, dogType : String, dogSex : String, dogBirth : String
            ) {

                dogInfo(Dog(dogBirth, dogIdx = 0,dogName,dogSex,dogType, surgery = "Y"))

            }
        })

        modifyDialog.setOnClickedListener(object : ModifyProfileCustomDialog.TextClickListener{
            override fun onClicked(
                dogName : String, dogType : String, dogSex : String, dogBirth : String
            ) {
                changedogInfo(Dog(dogBirth, dogIdx,dogName,dogSex,dogType, surgery = "Y"))
            }
        })

        //강아지 프로필 리싸이클러뷰
        dogRVdadapter = DogprofileRVAdapter()
        dogRVdadapter.setMyItemClickListener(object  : DogprofileRVAdapter.MyItemClickListener{
            override fun onItemClick(dog: Dog) {
                modifyDialog.MyDig()
                dogIdx=dog.dogIdx

            }
        })

        binding.mypageDogprofileRv.adapter=dogRVdadapter
        binding.mypageDogprofileRv.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.mypageDogprofileRv.overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        // mypage의 메뉴 클릭 리스너
        binding.mypagePushTv.setOnClickListener {
            val intent = Intent(activity, PushSettingActivity::class.java)
            startActivity(intent)
        }

        binding.mypageMyprofileTv.setOnClickListener {
            val intent = Intent(activity, MyProfileActivity::class.java)
            startActivity(intent)
        }

        binding.mypageMyActivityTv.setOnClickListener {
            val intent = Intent(activity, MyActiveActivity::class.java)
            startActivity(intent)
        }

        binding.mypageUpdateTv.setOnClickListener {
            val intent = Intent(activity, UpdateActivity::class.java)
            startActivity(intent)
        }

        binding.mypageAskTv.setOnClickListener {
            val intent = Intent(activity, AskActivity::class.java)
            startActivity(intent)
        }

        binding.mypageAppInfoTv.setOnClickListener {
            val intent = Intent(activity, AppinfoActivity::class.java)
            startActivity(intent)
        }

        binding.mypageLogoutTv.setOnClickListener {
            logout()
            signOut()
        }

    }

    fun changeUserImg(newImgUrl : String)
    {
        val imgUrl = newImgUrl
        val user = User("", imgUrl, "", "", "", "")

        Log.d("imgUrl", imgUrl)
        UserService.changeUserImg(this, user)
    }

    // Firebase Storage에 이미지를 업로드 하는 함수
    private fun uploadImageToFirebase(uri: Uri)
    {
        val storage : FirebaseStorage? = FirebaseStorage.getInstance()

        var fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss").format(Date())}_.png"
        var imagesRef = storage!!.reference.child("images/").child(fileName)

        imagesRef.putFile(uri!!).addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                
                Glide.with(this)
                    .load(it)
                    .into(binding.mypageMyImgIv)
                changeUserImg(it.toString())
            }
        }.addOnFailureListener{
            // 이미지 업로드 실패
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UserService.getUserByIdx(this)
    }


    override fun onStart() {
        super.onStart()

        DogService.getDoglist(this)

    }

    override fun onResume() {
        super.onResume()
        UserService.getUserByIdx(this)
    }

//    private fun initRecyclerView(){
//        //강아지 프로필 리싸이클러뷰
//        dogRVdadapter = DogprofileRVAdapter()
//        dogRVdadapter.setMyItemClickListener(object  : DogprofileRVAdapter.MyItemClickListener{
//            override fun onItemClick(dog: Dog) {
//                modifyDialog.MyDig()
//                dogIdx=dog.dogIdx
//
//            }
//        })
//
//        binding.mypageDogprofileRv.adapter=dogRVdadapter
//        binding.mypageDogprofileRv.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//        binding.mypageDogprofileRv.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
//
//    }
    private fun logout() {

        val editor = ApplicationClass.mSharedPreferences.edit()
        Log.d("LOGIN",editor.toString())
        editor.remove(X_AUTH_TOKEN)
        editor.apply()
        Log.d("LOGIN",editor.toString())

        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onMypageLoading() {


    }

    override fun onMypageSuccess(dogs :  ArrayList<Dog>) {
        dogRVdadapter.addDogs(dogs)
    }

    override fun onMypageFailure(code: Int, message: String) {

    }



    private fun dogInfo(dog : Dog) {

        DogService.dogInfo(this,dog)

    }

    private fun changedogInfo(dog : Dog) {

        DogService.setMypageDogChangeView(this)
        DogService.changeDog(dogIdx,dog)

    }
    override fun onDogInfoLoading() {


    }

    override fun onDogInfoSuccess(dogIdx: Dog) {

        Log.d("LOG_SUCCESS", "성공")
        DogService.getDoglist(this)


    }

    override fun onDogInfoFailure(code: Int, message: String) {
        Log.d("LOG_SUCCESS", "실패")
    }

    override fun onMypageDogchangeLoading() {


    }

    override fun onMypageDogchangeSuccess() {
        Log.d("LOG_SUCCESS", "성공")
        DogService.getDoglist(this)

    }

    override fun onMypageDogchangeFailure(code: Int, message: String) {

    }

    private fun signOut()
    {
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSingInClient = GoogleSignIn.getClient(requireContext(), gso)
        auth = FirebaseAuth.getInstance()

        FirebaseAuth.getInstance().signOut()
        googleSingInClient?.signOut()
    }

    fun getUser(user: User) {

        if(user.imgUrl != "" || user.imgUrl != null)
        {
            Glide.with(this)
                .load(user.imgUrl)
                .into(binding.mypageMyImgIv)
        }
        else
        {
            Glide.with(this)
                .load(R.drawable.ic_no_img2)
                .into(binding.mypageMyImgIv)
        }
        binding.mypageNameTv.text=user.nickName
    }

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

    }

    override fun onChangeprofileFailure(code: Int, message: String) {

    }


}