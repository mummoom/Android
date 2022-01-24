package com.mummoom.md.ui.main.mypage

import android.content.Intent
import com.mummoom.md.databinding.FragmentMypageBinding
import com.mummoom.md.ui.BaseFragment

class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) {

    override fun initAfterBinding() {

        val dialog = MypageCustomDialog(requireContext())
        binding.mypagePlusBtnIv.setOnClickListener {
            dialog.myDig()
        }

        dialog.setOnClickedListener(object : MypageCustomDialog.ButtonClickListener{
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

        binding.mypageMyScrapTv.setOnClickListener {
            val intent = Intent(activity, MyScrapActivity::class.java)
            startActivity(intent)
        }

        binding.mypageMyLikeTv.setOnClickListener {
            val intent = Intent(activity, MyLikedActivity::class.java)
            startActivity(intent)
        }

        binding.mypageMyCommentTv.setOnClickListener {
            val intent = Intent(activity, MyCommentActivity::class.java)
            startActivity(intent)
        }

        binding.mypageMySettingTv.setOnClickListener {
            val intent = Intent(activity, MySettingActivity::class.java)
            startActivity(intent)
        }

    }
}