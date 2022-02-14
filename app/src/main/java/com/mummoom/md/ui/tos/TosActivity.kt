package com.mummoom.md.ui.tos

import android.view.View
import com.mummoom.md.databinding.ActivityTosBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.siginup.SignUpActivity

class TosActivity: BaseActivity<ActivityTosBinding>(ActivityTosBinding::inflate),View.OnClickListener {

    override fun initAfterBinding() {

        // 뒤로가기 버튼 눌렀을 때
        binding.tosBackIv.setOnClickListener {
            finish()
        }
        binding.tosAllAgreeOffIv.setOnClickListener {
            binding.tosAllAgreeOffIv.visibility=View.INVISIBLE
            binding.tosAllAgreeOnIv.visibility=View.VISIBLE
            binding.tosUseAgreeOffIv.visibility=View.INVISIBLE
            binding.tosUseAgreeOnIv.visibility=View.VISIBLE
            binding.tosPrivacyAgreeOffIv.visibility=View.INVISIBLE
            binding.tosPrivacyAgreeOnIv.visibility=View.VISIBLE
        }
        binding.tosAllAgreeOnIv.setOnClickListener {
            binding.tosAllAgreeOffIv.visibility=View.VISIBLE
            binding.tosAllAgreeOnIv.visibility=View.INVISIBLE
            binding.tosUseAgreeOffIv.visibility=View.VISIBLE
            binding.tosUseAgreeOnIv.visibility=View.INVISIBLE
            binding.tosPrivacyAgreeOffIv.visibility=View.VISIBLE
            binding.tosPrivacyAgreeOnIv.visibility=View.INVISIBLE
        }
        binding.tosUseAgreeOffIv.setOnClickListener {
            binding.tosUseAgreeOffIv.visibility=View.INVISIBLE
            binding.tosUseAgreeOnIv.visibility=View.VISIBLE
        }
        binding.tosUseAgreeOnIv.setOnClickListener {
            binding.tosUseAgreeOffIv.visibility=View.VISIBLE
            binding.tosUseAgreeOnIv.visibility=View.INVISIBLE
        }
        binding.tosPrivacyAgreeOffIv.setOnClickListener {
            binding.tosPrivacyAgreeOffIv.visibility=View.INVISIBLE
            binding.tosPrivacyAgreeOnIv.visibility=View.VISIBLE
        }


        binding.tosPrivacyAgreeOnIv.setOnClickListener {
            binding.tosPrivacyAgreeOffIv.visibility=View.VISIBLE
            binding.tosPrivacyAgreeOnIv.visibility=View.INVISIBLE
        }

        binding.tosOkayBtn.setOnClickListener {
            startActivityWithClear(SignUpActivity::class.java)
        }
        binding.tosUseMoreBtn.setOnClickListener(this)
        binding.tosPrivacyMoreBtn.setOnClickListener(this)
        if((binding.tosPrivacyAgreeOnIv.visibility==View.VISIBLE)&&(binding.tosUseAgreeOnIv.visibility==View.VISIBLE)) {
            binding.tosAllAgreeOnIv.visibility=View.VISIBLE
        }


    }
    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.tosUseMoreBtn-> {
                if(binding.tosUseContentCl.visibility==View.GONE) {
                    binding.tosUseContentCl.visibility = View.VISIBLE
                    binding.tosUseMoreBtn.animate().setDuration(200).rotation(180f)
                }

                else if(binding.tosUseContentCl.visibility==View.VISIBLE) {
                    binding.tosUseContentCl.visibility=View.GONE
                    binding.tosUseMoreBtn.animate().setDuration(200).rotation(0f)
                }


            }
            binding.tosPrivacyMoreBtn -> {
                if(binding.tosPrivacyContentCl.visibility==View.INVISIBLE) {
                    binding.tosPrivacyContentCl.visibility = View.VISIBLE
                    binding.tosUseMoreBtn.animate().setDuration(200).rotation(180f)
                }
                else
                {
                    binding.tosPrivacyContentCl.visibility=View.INVISIBLE
                    binding.tosPrivacyMoreBtn.animate().setDuration(200).rotation(0f)
                }

            }


        }
    }
}