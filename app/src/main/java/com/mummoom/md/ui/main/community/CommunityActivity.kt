package com.mummoom.md.ui.main.community

import com.minux.meommum.ui.main.mypage.MypageCustomDialog
import com.mummoom.md.databinding.ActivityCommunityBinding
import com.mummoom.md.ui.BaseActivity


class CommunityActivity : BaseActivity<ActivityCommunityBinding>(ActivityCommunityBinding::inflate) {

    override fun initAfterBinding() {

        val dialog = MypageCustomDialog(this)

        binding.communityActMoreBtnIv.setOnClickListener {
            dialog.MyDig()
        }
    }
}