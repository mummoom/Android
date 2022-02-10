package com.mummoom.md.ui.main.community

import com.mummoom.md.R
import com.mummoom.md.databinding.ActivityWritingdetailBinding
import com.mummoom.md.ui.BaseActivity

class WritingDetailActivity : BaseActivity<ActivityWritingdetailBinding>(ActivityWritingdetailBinding::inflate) {

    private var isLike = false
    private var isScrap = false

    override fun initAfterBinding() {

        // 좋아요 버튼
        binding.writingDetailHeartIv.setOnClickListener {

            if(!isLike)
            {
                binding.writingDetailHeartIv.setImageResource(R.drawable.ic_heart_on)
                isLike = !isLike
            }
            else
            {
                binding.writingDetailHeartIv.setImageResource(R.drawable.ic_heart_off)
                isLike = !isLike
            }
        }

        // 스크랩 버튼
        binding.writingDetailScrapIv.setOnClickListener {

            if(!isScrap)
            {
                binding.writingDetailScrapIv.setImageResource(R.drawable.ic_scrap)
                isScrap = !isScrap
            }
            else
            {
                binding.writingDetailScrapIv.setImageResource(R.drawable.ic_scrap)
                isScrap = !isScrap
            }
        }



    }
}