package com.mummoom.md.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.mummoom.md.R
import com.mummoom.md.databinding.FragmentHomeBinding
import com.mummoom.md.ui.BaseFragment

class HomeFragment(): BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    override fun initAfterBinding() {

        //
        val bannerAdapter = HomeBannerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.home_banner_ex))
        bannerAdapter.addFragment(BannerFragment(R.drawable.home_banner_ex))

        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        // home 클릭 리스너
        //음식 종류별
        binding.homeMeatCv.setOnClickListener{
            val intent = Intent(activity, FoodinfoActivity::class.java )
            startActivity(intent)
        }

        binding.homeVegCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            startActivity(intent)
        }

        binding.homeFruitCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            startActivity(intent)
        }

        binding.homeDairyCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            startActivity(intent)
        }

        binding.homeSeafoodCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            startActivity(intent)
        }

        binding.homeEtcCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            startActivity(intent)
        }

        //음식 정보
        binding.homeEatGoodCv.setOnClickListener{
            val intent = Intent(activity, FoodeatActivity::class.java )
            startActivity(intent)
        }

        binding.homeEatSosoCv.setOnClickListener{
            val intent = Intent(activity, FoodeatActivity::class.java )
            startActivity(intent)
        }

        binding.homeEatNeverCv.setOnClickListener{
            val intent = Intent(activity, FoodeatActivity::class.java )
            startActivity(intent)
        }
    }


}