package com.mummoom.md.ui.main.mypage

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.mummoom.md.ApplicationClass
import com.mummoom.md.ApplicationClass.Companion.X_AUTH_TOKEN
import com.mummoom.md.R
import com.mummoom.md.data.entities.Dog
import com.mummoom.md.data.remote.Dog.DogService
import com.mummoom.md.databinding.FragmentMypageBinding
import com.mummoom.md.ui.BaseFragment
import com.mummoom.md.ui.doggender.DogInfoView
import com.mummoom.md.ui.doginfocheck.DogInfoCheckActivity
import com.mummoom.md.ui.login.LoginActivity
import com.mummoom.md.ui.main.community.MypageCustomDialog

class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) ,MypageView,DogInfoView,MypageDogChangeView{
    private lateinit var dogRVdadapter : DogprofileRVAdapter
    var dogIdx :Int =0
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient : GoogleSignInClient


    override fun initAfterBinding() {

        // 다이얼로그 변수
        val plusDialog = MypageCustomDialog(requireContext())
        val modifyDialog = ModifyProfileCustomDialog(requireContext())


        // 강아지 프로필 추가
        binding.mypageDogprofilePlusIv.setOnClickListener {
//            val items = getResources().getStringArray(R.array.year)
            plusDialog.MyDig()
        }

        // 강아지 프로필 수정
        binding.mypageMoreBtnIv.setOnClickListener {
            val items = getResources().getStringArray(R.array.year)
            modifyDialog.MyDig()
        }


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


        // mypage의 메뉴 클릭 리스너
        binding.mypageUpdateTv.setOnClickListener {
//            val intent = Intent(activity, MyProfileActivity::class.java)
//            startActivity(intent)
        }
        binding.mypagePushTv.setOnClickListener {
            val intent = Intent(activity, PushSettingActivity::class.java)
            startActivity(intent)
        }
        binding.mypageAskTv.setOnClickListener {
//            val intent = Intent(activity, MyProfileActivity::class.java)
//            startActivity(intent)
        }

        binding.mypageMyprofileTv.setOnClickListener {
            val intent = Intent(activity, MyProfileActivity::class.java)
            startActivity(intent)
        }

        binding.mypageMyActivityTv.setOnClickListener {
            val intent = Intent(activity, MyActiveActivity::class.java)
            startActivity(intent)

        }

        binding.mypageLogoutTv.setOnClickListener {
            logout()
            signOut()
        }


//                    (context as MainActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, AlbumFragment())
//                .commitAllowingStateLoss()

    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
        DogService.getDoglist(this)
    }

    override fun onResume() {
        super.onResume()

    }

    private fun initRecyclerView(){
        //강아지 프로필 리싸이클러뷰
        dogRVdadapter = DogprofileRVAdapter()
        dogRVdadapter.setMyItemClickListener(object  : DogprofileRVAdapter.MyItemClickListener{
            override fun onItemClick(dog: Dog) {
                binding.mypageDogNameTv.text=dog.dogName
                binding.mypageDogbirthTv.text=dog.dogBirth
                binding.mypageDogtypeTv.text=dog.dogType
                if(dog.dogSex=="0")
                    binding.mypageDogGenderTv.text="남아"
                else binding.mypageDogGenderTv.text="여아"
                dogIdx=dog.dogIdx
                Log.d("DOGIDX",dogIdx.toString())

            }

        })

        binding.mypageDogprofileRv.adapter=dogRVdadapter
        binding.mypageDogprofileRv.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.mypageDogprofileRv.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }
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


}