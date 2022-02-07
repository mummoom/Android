package com.mummoom.md.ui.main.home

import android.view.View
import com.bumptech.glide.Glide
import com.mummoom.md.R
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.remote.Ingredients.IngredientsView
import com.mummoom.md.databinding.ActivityFoodinfoBinding
import com.mummoom.md.ui.BaseActivity


class FoodinfoActivity : BaseActivity<ActivityFoodinfoBinding>(ActivityFoodinfoBinding::inflate),View.OnClickListener{


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

    override fun onStart() {
        super.onStart()
        if(intent.hasExtra("ingredientInfo"))
        {
            initView(intent.getSerializableExtra("ingredientInfo") as Ingredients)
        }
    }

    private fun initView(ingredientInfo : Ingredients)
    {
        // 이미지
        Glide.with(this)
            .load(ingredientInfo.imgUrl)
            .into(binding.foodImgIv)

        // 이름
        binding.foodinfoNameTv.text = ingredientInfo.name

        // 칼로리
        binding.foodinfoKcalTv.text = ingredientInfo.kcal.toString() + " kcal"

        // 유의해야할 점
        binding.foodinfoWarnEatContTv.text = ingredientInfo.warning

        // 특징
        binding.foodinfoSpecContTv.text = ingredientInfo.spec

        // 효과
        binding.foodinfoEffectContTv.text = ingredientInfo.component.effect

        //영양 정보
        binding.foodinfoNutritionTanValueTv.text = ingredientInfo.component.tan.toString() + " g"
        binding.foodinfoNutritionDanValueTv.text = ingredientInfo.component.dan.toString() + " g"
        binding.foodinfoNutritionGiValueTv.text = ingredientInfo.component.gi.toString() + " g"
        binding.foodinfoNutritionMuValueTv.text = ingredientInfo.component.mu.toString() + " g"
        binding.foodinfoNutritionWaterValueTv.text = ingredientInfo.component.water.toString() + "g"


        // 발바닥 점수
        if(ingredientInfo.score == 1)
        {
            binding.foodinfoFoot1Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot2Iv.setImageResource(R.drawable.footprint_off)
            binding.foodinfoFoot3Iv.setImageResource(R.drawable.footprint_off)
            binding.foodinfoFoot4Iv.setImageResource(R.drawable.footprint_off)
            binding.foodinfoFoot5Iv.setImageResource(R.drawable.footprint_off)
        }
        if(ingredientInfo.score == 2)
        {
            binding.foodinfoFoot1Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot2Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot3Iv.setImageResource(R.drawable.footprint_off)
            binding.foodinfoFoot4Iv.setImageResource(R.drawable.footprint_off)
            binding.foodinfoFoot5Iv.setImageResource(R.drawable.footprint_off)
        }
        if(ingredientInfo.score == 3)
        {
            binding.foodinfoFoot1Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot2Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot3Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot4Iv.setImageResource(R.drawable.footprint_off)
            binding.foodinfoFoot5Iv.setImageResource(R.drawable.footprint_off)
        }
        if(ingredientInfo.score == 4)
        {
            binding.foodinfoFoot1Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot2Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot3Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot4Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot5Iv.setImageResource(R.drawable.footprint_off)
        }
        if(ingredientInfo.score == 5)
        {
            binding.foodinfoFoot1Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot2Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot3Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot4Iv.setImageResource(R.drawable.footprint)
            binding.foodinfoFoot5Iv.setImageResource(R.drawable.footprint)
        }

    }


}