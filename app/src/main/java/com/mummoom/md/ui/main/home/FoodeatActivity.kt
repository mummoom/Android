package com.mummoom.md.ui.main.home

import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.remote.Ingredients.IngredientsView
import com.mummoom.md.databinding.ActivityFoodeatBinding
import com.mummoom.md.ui.BaseActivity

class FoodeatActivity : BaseActivity<ActivityFoodeatBinding>(ActivityFoodeatBinding::inflate),
    IngredientsView {

    override fun initAfterBinding() {

        binding.foodeatBackIv.setOnClickListener {
            finish()
        }

    }

    override fun onIngredientsLoading() {
        TODO("Not yet implemented")
    }

    override fun onIngredientsSuccess(ingredients: ArrayList<Ingredients>) {
        TODO("Not yet implemented")
    }

    override fun onIngredientsFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }
}