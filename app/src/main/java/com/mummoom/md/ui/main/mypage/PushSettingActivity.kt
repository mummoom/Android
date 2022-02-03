package com.mummoom.md.ui.main.mypage

import android.view.View
import com.mummoom.md.databinding.ActivityIllustrationBinding
import com.mummoom.md.databinding.ActivityPushSettingBinding
import com.mummoom.md.ui.BaseActivity

class PushSettingActivity: BaseActivity<ActivityPushSettingBinding>(ActivityPushSettingBinding::inflate) {

    override fun initAfterBinding() {

        // 뒤로가기 버튼 눌렀을 때
        binding.pushSettingBackarrowBtn.setOnClickListener {
            finish()
        }
        binding.pushSettingAllNotificationsOffIv.setOnClickListener {
            binding.pushSettingAllNotificationsOffIv.visibility=View.INVISIBLE
            binding.pushSettingAllNotificationsOnIv.visibility=View.VISIBLE
            binding.pushSettingEventOrBenefitOffIv.visibility=View.INVISIBLE
            binding.pushSettingEventOrBenefitOnIv.visibility=View.VISIBLE
            binding.pushSettingPostOffIv.visibility=View.INVISIBLE
            binding.pushSettingPostOnIv.visibility=View.VISIBLE
        }
        binding.pushSettingEventOrBenefitOffIv.setOnClickListener {
            binding.pushSettingEventOrBenefitOffIv.visibility=View.INVISIBLE
            binding.pushSettingEventOrBenefitOnIv.visibility=View.VISIBLE
        }
        binding.pushSettingPostOffIv.setOnClickListener {
            binding.pushSettingPostOffIv.visibility=View.VISIBLE
            binding.pushSettingPostOnIv.visibility=View.INVISIBLE
        }
        binding.pushSettingAllNotificationsOnIv.setOnClickListener {
            binding.pushSettingAllNotificationsOffIv.visibility=View.VISIBLE
            binding.pushSettingAllNotificationsOnIv.visibility=View.INVISIBLE
            binding.pushSettingEventOrBenefitOffIv.visibility=View.VISIBLE
            binding.pushSettingEventOrBenefitOnIv.visibility=View.INVISIBLE
            binding.pushSettingPostOffIv.visibility=View.VISIBLE
            binding.pushSettingPostOnIv.visibility=View.INVISIBLE
        }
        binding.pushSettingEventOrBenefitOnIv.setOnClickListener {
            binding.pushSettingEventOrBenefitOffIv.visibility=View.INVISIBLE
            binding.pushSettingEventOrBenefitOnIv.visibility=View.VISIBLE
        }
        binding.pushSettingPostOnIv.setOnClickListener {
            binding.pushSettingPostOffIv.visibility=View.VISIBLE
            binding.pushSettingPostOnIv.visibility=View.INVISIBLE
        }

        binding.pushSettingConfirmBtn.setOnClickListener {
            finish()
        }


    }
}