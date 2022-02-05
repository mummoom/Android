package com.mummoom.md.ui.main.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.mummoom.md.databinding.ActivityFoodtypeBinding
import com.mummoom.md.ui.BaseActivity

class FoodtypeActivity : BaseActivity<ActivityFoodtypeBinding>(ActivityFoodtypeBinding::inflate){

    override fun initAfterBinding() {

        binding.foodtypeInfoRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        binding.foodtypeBackIv.setOnClickListener {
            finish()
        }
    }

}