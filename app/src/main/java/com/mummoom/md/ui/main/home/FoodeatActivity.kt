package com.mummoom.md.ui.main.home

import com.mummoom.md.databinding.ActivityFoodeatBinding
import com.mummoom.md.ui.BaseActivity

class FoodeatActivity : BaseActivity<ActivityFoodeatBinding>(ActivityFoodeatBinding::inflate){

    override fun initAfterBinding() {

        binding.foodeatBackIv.setOnClickListener {
            finish()
        }

    }
}