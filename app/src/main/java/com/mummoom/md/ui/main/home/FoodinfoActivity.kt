package com.mummoom.md.ui.main.home

import android.view.View
import android.widget.Toast
import com.mummoom.md.databinding.ActivityFoodinfoBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogname.DognameActivity


class FoodinfoActivity : BaseActivity<ActivityFoodinfoBinding>(ActivityFoodinfoBinding::inflate), View.OnClickListener{

    override fun initAfterBinding() {

        binding.foodinfoBackIv.setOnClickListener {
            finish()
        }

        binding.infoNutritionTitleCl.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.infoNutritionTitleCl ->
                if (binding.infoExpand1.visibility == View.INVISIBLE) {
                    binding.infoExpand1.visibility = View.VISIBLE
                    binding.infoNutritionMoreIv.animate().setDuration(200).rotation(180f)

                } else if(binding.infoExpand1.visibility == View.VISIBLE) {
                    binding.infoExpand1.visibility = View.GONE
                    binding.infoNutritionMoreIv.animate().setDuration(200).rotation(0f)
                }

        }



        }
    }



