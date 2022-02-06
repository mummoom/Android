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
        binding.homeVegCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",0)
            intent.putExtra("category","채소")
            startActivity(intent)
        }

        binding.homeFishCv.setOnClickListener{
            val intent = Intent(requireContext(), FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",1)
            intent.putExtra("category","생선")
            startActivity(intent)
        }

        binding.homeMeatCv.setOnClickListener{
            val intent = Intent(requireContext(), FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",2)
            intent.putExtra("category","고기")
            startActivity(intent)
        }

        binding.homeDairyCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",3)
            intent.putExtra("category","유제품")
            startActivity(intent)
        }

        binding.homeSeafoodCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",4)
            intent.putExtra("category","해산물")
            startActivity(intent)
        }

        binding.homeRiceCv.setOnClickListener{
            val intent = Intent(requireContext(), FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",5)
            intent.putExtra("category","곡류")
            startActivity(intent)

        }

        binding.homeFruitCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",6)
            intent.putExtra("category","과일")
            startActivity(intent)
        }

        binding.homeEtcCv.setOnClickListener{
            val intent = Intent(activity, FoodtypeActivity::class.java )
            intent.putExtra("categoryNum",7)
            intent.putExtra("category","기타")
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