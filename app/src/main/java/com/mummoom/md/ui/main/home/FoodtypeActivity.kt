package com.mummoom.md.ui.main.home

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.mummoom.md.databinding.ActivityFoodtypeBinding
import com.mummoom.md.ui.BaseActivity

class FoodtypeActivity : BaseActivity<ActivityFoodtypeBinding>(ActivityFoodtypeBinding::inflate),IngredientsView{

    override fun initAfterBinding() {

        binding.foodtypeInfoRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        binding.foodtypeBackIv.setOnClickListener {
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