package com.mummoom.md.ui.main.home

import android.content.Intent
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
        binding.homeFishCv.setOnClickListener{
            val intent = Intent(requireContext(), FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",1)
            startActivity(intent)
        }

        binding.homeRiceCv.setOnClickListener{
            val intent = Intent(requireContext(), FoodtypeActivity::class.java )
            startActivity(intent)
            intent.putExtra("categoryNum",5)

        }

        binding.homeMeatCv.setOnClickListener{
            val intent = Intent(requireContext(), FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",2)
            startActivity(intent)
        }

        binding.homeVegCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",0)
            startActivity(intent)
        }

        binding.homeFruitCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",6)
            startActivity(intent)
        }

        binding.homeDairyCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",3)
            startActivity(intent)
        }

        binding.homeSeafoodCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",4)
            startActivity(intent)
        }

        binding.homeEtcCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",7)
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