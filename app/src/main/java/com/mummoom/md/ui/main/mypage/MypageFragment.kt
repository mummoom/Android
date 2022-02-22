package com.mummoom.md.ui.main.mypage

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
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
import com.mummoom.md.data.remote.Dog.DeleteDogView
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

class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) ,MypageView,DogInfoView,MypageDogChangeView,
    MyprofileView, ChangeImgView, DeleteDogView{

    private lateinit var dogRVdadapter : DogprofileRVAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient : GoogleSignInClient
    private lateinit var animation : Animation

//    val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.rotate)


    var dogIdx :Int = 0
    private var uri : Uri? = null

    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent())
    {
        uri = it

        if(uri != null)
        {
            uploadImageToFirebase(uri!!)
        }

    }

    override fun initAfterBinding() {

        // 다이얼로그 변수
        val plusDialog = MypageCustomDialog(requireContext())
        val modifyDialog = ModifyProfileCustomDialog(requireContext())
        val changeImageDialog = ChangeImageCustomDialog(requireContext())
        val dogProfileDialog = DogProfileMoreBtnDialog(requireContext())

        animation = AnimationUtils.loadAnimation(requireContext(),R.anim.rotate)

        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        // 강아지 프로필 추가 버튼 눌렀을 때
        binding.mypageDogprofilePlusIv.setOnClickListener {
            plusDialog.MyDig()
        }

        // 강아지 프로필 추가 다이얼로그에서 입력 값 처리 부분 코드
        plusDialog.setOnClickedListener(object : MypageCustomDialog.TextClickListener{
            override fun onClicked(
                dogName : String, dogType : String, dogSex : String, dogBirth : String
            ) {

                dogInfo(Dog(dogBirth, dogIdx = 0,dogName,dogSex,dogType, surgery = "Y"))

            }
        })

        // 내 프로필 사진 변경 버튼(톱니바퀴) 눌렀을 때
        binding.mypageMysettingIv.setOnClickListener {
            changeImageDialog.MyDig()
        }

        // 프로필 수정 다이얼로그에서 선택지 따른 코드 부분(갤러리에서 선택 / 기본 일러스트 선택)
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
                binding.mypageRotateIv.visibility = View.VISIBLE
                binding.mypageLoadingIv.visibility = View.VISIBLE
                binding.mypageRotateIv.startAnimation(animation)

                binding.mypageMyImgIv.setImageResource(R.drawable.ic_no_img2)
                changeUserImg("")
            }
        })


        //강아지 프로필 리싸이클러뷰
        dogRVdadapter = DogprofileRVAdapter()
        dogRVdadapter.setMyItemClickListener(object  : DogprofileRVAdapter.MyItemClickListener{
            //강아지 피드의 더보기 버튼 클릭했을 때
            override fun onItemClick(dog: Dog) {
                // 더보기 버튼 다이얼로그 띄우고 선택지에 따른 리스너 작성하기(강아지 수정/강아지 삭제)
                dogIdx = dog.dogIdx
                dogProfileDialog.MyDig()
            }
        })

        dogProfileDialog.setOnClickedListener(object : DogProfileMoreBtnDialog.clickListener{
            // 다이얼로그 중 강아지 수정 클릭했을 때
            override fun onEditProfile() {
                modifyDialog.MyDig()
            }

            // 다이얼로그 중 강아지 삭제 클릭했을 때
            override fun onDeleteProfile() {
                deleteDog()
            }

        })

        // 강아지 수정 다이얼로그에서 입력 값 처리 부분
        modifyDialog.setOnClickedListener(object : ModifyProfileCustomDialog.TextClickListener{
            override fun onClicked(
                dogName : String, dogType : String, dogSex : String, dogBirth : String
            ) {
                changedogInfo(Dog(dogBirth, dogIdx,dogName,dogSex,dogType, surgery = "Y"))
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

    fun deleteDog()
    {
        val deleteDogService = DogService
        deleteDogService.setDeleteDogView(this)
        deleteDogService.deleteDog(dogIdx)
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
        binding.mypageRotateIv.visibility = View.VISIBLE
        binding.mypageLoadingIv.visibility = View.VISIBLE
        binding.mypageRotateIv.startAnimation(animation)

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
        Log.d("ONSTATE", "oncreate")
        UserService.getUserByIdx(this)
    }


    override fun onStart() {
        super.onStart()

        Log.d("ONSTATE", "onstart")
        UserService.getUserByIdx(this)
        DogService.getDoglist(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d("ONSTATE", "onresume")
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

        if(user.imgUrl == null || user.imgUrl == "")
        {
            Glide.with(this)
                .load(R.drawable.ic_no_img2)
                .into(binding.mypageMyImgIv)
        }
        else
        {
            Glide.with(this)
                .load(user.imgUrl)
                .into(binding.mypageMyImgIv)
        }

        binding.mypageNameTv.text=user.nickName
    }

    // 프로필 정보를 불러오는 API
    override fun onMyprofileLoading() {

    }

    override fun onMyprofileSuccess(user: User) {
        getUser(user)
    }

    override fun onMyprofileFailure(code: Int, message: String) {

    }

    // 프로필 이미지 수정하는 API
    override fun onChangeprofileLoading() {

//        binding.mypageRotateIv.visibility = View.VISIBLE
//        binding.mypageLoadingIv.visibility = View.VISIBLE
//        binding.mypageRotateIv.startAnimation(animation)
    }

    override fun onChangeprofileSuccess() {
        binding.mypageRotateIv.animation.cancel()
        binding.mypageRotateIv.visibility = View.GONE
        binding.mypageLoadingIv.visibility = View.GONE
    }

    override fun onChangeprofileFailure(code: Int, message: String) {
        binding.mypageRotateIv.animation.cancel()
        binding.mypageRotateIv.visibility = View.GONE
        binding.mypageLoadingIv.visibility = View.GONE
    }

    // dog를 delete하는 API 부분
    override fun onDeleteDogLoading() {

    }

    override fun onDeleteDogSuccess() {
        showToast("강아지 정보가 삭제되었습니다.")
        DogService.getDoglist(this)
    }

    override fun onDeleteDogFailure(code: Int, message: String) {
        when(code)
        {
            3000 -> showToast("오류가 발생하였습니다.")
            6005 -> showToast("존재하지 않는 강아지 정보입니다.")
            6006 -> showToast("유효하지 않은 유저입니다.")
            else -> showToast("오류가 발생하였습니다.")
        }
    }


}