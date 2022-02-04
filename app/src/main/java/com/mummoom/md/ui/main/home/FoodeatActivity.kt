package com.mummoom.md.ui.main.home

import com.mummoom.md.databinding.ActivityFoodeatBinding
import com.mummoom.md.ui.BaseActivity

class FoodeatActivity : BaseActivity<ActivityFoodeatBinding>(ActivityFoodeatBinding::inflate), IngredientsView{

    override fun initAfterBinding() {

        binding.foodeatBackIv.setOnClickListener {
            finish()
        }

    }

    override fun onIngredientsLoading() {
        TODO("Not yet implemented")
    }

    override fun onIngredientsSuccess() {
        TODO("Not yet implemented")
    }

    override fun onIngredientsFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }
}