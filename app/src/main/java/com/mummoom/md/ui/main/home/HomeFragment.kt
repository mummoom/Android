package com.mummoom.md.ui.main.home

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

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bannerAdapter = HomeBannerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.home_banner_ex))
        bannerAdapter.addFragment(BannerFragment(R.drawable.home_banner_ex2))

        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }



}