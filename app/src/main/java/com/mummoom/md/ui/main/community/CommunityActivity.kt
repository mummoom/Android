package com.mummoom.md.ui.main.community

import com.mummoom.md.databinding.ActivityCommunityBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.main.mypage.MypageCustomDialog


class CommunityActivity : BaseActivity<ActivityCommunityBinding>(ActivityCommunityBinding::inflate) {

    override fun initAfterBinding() {

        val dialog = MypageCustomDialog(this)

        binding.communityActMoreBtnIv.setOnClickListener {
            dialog.MyDig()
        }
    }
}