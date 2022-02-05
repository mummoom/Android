package com.mummoom.md.ui.main.home

import android.view.View
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.remote.Ingredients.IngredientsView
import com.mummoom.md.databinding.ActivityFoodinfoBinding
import com.mummoom.md.ui.BaseActivity


class FoodinfoActivity : BaseActivity<ActivityFoodinfoBinding>(ActivityFoodinfoBinding::inflate),
    IngredientsView, View.OnClickListener{


    override fun initAfterBinding() {

        binding.foodinfoBackIv.setOnClickListener {
            finish()
        }

        binding.foodinfoNutritionTitleCl.setOnClickListener(this)


    }

    override fun onClick(v: View?) {

        if(v == null) return

        when(v) {
            binding.foodinfoNutritionTitleCl ->
                if (binding.foodinfoNutritionContCl.visibility == View.GONE) {
                    binding.foodinfoNutritionContCl.visibility = View.VISIBLE
                    binding.foodinfoNutritionMoreIv.animate().setDuration(200).rotation(180f)

                } else{
                    binding.foodinfoNutritionContCl.visibility = View.GONE
                    binding.foodinfoNutritionMoreIv.animate().setDuration(200).rotation(0f)
                }

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