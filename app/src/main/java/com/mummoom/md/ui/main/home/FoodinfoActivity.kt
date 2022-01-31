package com.mummoom.md.ui.main.home

import android.view.View
import com.mummoom.md.databinding.ActivityFoodinfoBinding
import com.mummoom.md.ui.BaseActivity


class FoodinfoActivity : BaseActivity<ActivityFoodinfoBinding>(ActivityFoodinfoBinding::inflate){

    override fun initAfterBinding() {

        binding.foodinfoBackIv.setOnClickListener {
            finish()
        }


        binding.infoNutritionTitleCl.setOnClickListener {

            if(binding.infoNutritionContTv.visibility == View.VISIBLE){
                binding.infoNutritionContTv.visibility == View.GONE
                binding.infoNutritionMoreIv.animate().setDuration(200).rotation(180f)
            } else{
                binding.infoNutritionContTv.visibility == View.VISIBLE
                binding.infoNutritionMoreIv.animate().setDuration(200).rotation(0f)
            }
        }



    }
}