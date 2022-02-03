package com.mummoom.md.ui.main.mypage

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.mummoom.md.R
import com.mummoom.md.databinding.FragmentMypageBinding
import com.mummoom.md.ui.BaseFragment
import com.mummoom.md.ui.main.community.MypageCustomDialog

class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) {

    override fun initAfterBinding() {

        val plusDialog = MypageCustomDialog(requireContext())
        val modifyDialog = ModifyProfileCustomDialog(requireContext())

        // 강아지 프로필 추가
        binding.mypagePlusBtnIv.setOnClickListener {
            val items = getResources().getStringArray(R.array.year)
            plusDialog.MyDig()
        }

        // 강아지 프로필 수정
        binding.mypageMoreBtnIv.setOnClickListener {
            val items = getResources().getStringArray(R.array.year)
            modifyDialog.MyDig()
        }


        plusDialog.setOnClickedListener(object : MypageCustomDialog.TextClickListener{
            override fun onClicked(
                myName: String,
                mySpecies: String,
                myGender: String,
                myBirth: String
            ) {
                binding.mypagePuppyNameTv.text = myName
                binding.mypagePuppyInfoTv.text = mySpecies + " / " + myBirth
                binding.mypagePuppyGenderTv.text = myGender
            }
        })


        // mypage의 메뉴 클릭 리스너

        binding.mypageMyProfileTv.setOnClickListener {
            val intent = Intent(activity, MyProfileActivity::class.java)
            startActivity(intent)
        }

        binding.mypageMyCommunityTv.setOnClickListener {
            val intent = Intent(activity, MyCommunityActivity::class.java)
            startActivity(intent)
        }

        binding.mypageMyLikeTv.setOnClickListener {
            val intent = Intent(activity, MyLikedActivity::class.java)
            startActivity(intent)
        }

        binding.mypageMySettingTv.setOnClickListener {
            val intent = Intent(activity, MySettingActivity::class.java)
            startActivity(intent)
        }

//                    (context as MainActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, AlbumFragment())
//                .commitAllowingStateLoss()

    }




}