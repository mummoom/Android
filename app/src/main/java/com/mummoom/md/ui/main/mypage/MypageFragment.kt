package com.mummoom.md.ui.main.mypage

import android.content.Intent
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.mummoom.md.R
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.entities.Dog
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.Dog.DogService
import com.mummoom.md.data.remote.User.UserService
import com.mummoom.md.databinding.FragmentMypageBinding
import com.mummoom.md.ui.main.home.BannerFragment
import com.mummoom.md.ui.BaseFragment
import com.mummoom.md.ui.BaseViewpagerAdapter
import com.mummoom.md.ui.main.community.MypageCustomDialog
import com.mummoom.md.ui.main.home.FoodinfoActivity
import com.mummoom.md.ui.main.home.FoodtypeRVAdapter

class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) ,MypageView{
    private lateinit var dogRVdadapter : DogprofileRVAdapter

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
                myName: String,
                mySpecies: String,
                myGender: String,
                myBirth: String
            ) {
                binding.mypageDogNameTv.text = myName
                binding.mypageDogbirthTv.text =myBirth
                binding.mypageDogtypeTv.text=mySpecies
                binding.mypagePuppyGenderTv.text = myGender
            }
        })

        modifyDialog.setOnClickedListener(object : ModifyProfileCustomDialog.TextClickListener{
            override fun onClicked(
                myName: String,
                mySpecies: String,
                myGender: String,
                myBirth: String
            ) {
                binding.mypageDogNameTv.text = myName
                binding.mypageDogbirthTv.text =myBirth
                binding.mypageDogtypeTv.text=mySpecies
                binding.mypagePuppyGenderTv.text = myGender
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

        binding.mypageSettingTv.setOnClickListener {
            val intent = Intent(activity, MySettingActivity::class.java)
            startActivity(intent)
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

    private fun initRecyclerView(){
        //강아지 프로필 리싸이클러뷰
        dogRVdadapter = DogprofileRVAdapter()
        dogRVdadapter.setMyItemClickListener(object  : DogprofileRVAdapter.MyItemClickListener{
            override fun onItemClick(dog: Dog) {
                binding.mypageDogNameTv.text=dog.dogName
                binding.mypageDogbirthTv.text=dog.dogBirth
                binding.mypageDogtypeTv.text=dog.dogType

            }

        })

        binding.mypageDogprofileRv.adapter=dogRVdadapter
        binding.mypageDogprofileRv.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)



    }


    override fun onMypageLoading() {

    }

    override fun onMypageSuccess(dogs :  ArrayList<Dog>) {
        dogRVdadapter.addDogs(dogs)
    }

    override fun onMypageFailure(code: Int, message: String) {

    }

}