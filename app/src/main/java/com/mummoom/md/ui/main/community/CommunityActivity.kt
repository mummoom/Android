package com.mummoom.md.ui.main.community

import com.mummoom.md.R
import com.mummoom.md.databinding.ActivityCommunityBinding
import com.mummoom.md.ui.BaseActivity


class CommunityActivity : BaseActivity<ActivityCommunityBinding>(ActivityCommunityBinding::inflate) {

    private var isLike = false
    private var isScrap = false

    override fun initAfterBinding() {

        // 좋아요 버튼
        binding.communityActHeartIv.setOnClickListener {

            if(!isLike)
            {
                binding.communityActHeartIv.setImageResource(R.drawable.ic_heart_on)
                isLike = !isLike
            }
            else
            {
                binding.communityActHeartIv.setImageResource(R.drawable.ic_heart_off)
                isLike = !isLike
            }
        }

        // 스크랩 버튼
        binding.communityActScrapIv.setOnClickListener {

            if(!isScrap)
            {
                binding.communityActScrapIv.setImageResource(R.drawable.ic_scrap)
                isScrap = !isScrap
            }
            else
            {
                binding.communityActScrapIv.setImageResource(R.drawable.ic_scrap)
                isScrap = !isScrap
            }
        }



    }
}