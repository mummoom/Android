package com.mummoom.md.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mummoom.md.databinding.FragmentBannerBinding
import com.mummoom.md.databinding.FragmentHomeBinding
import com.mummoom.md.ui.BaseFragment

//class BannerFragment(val imgRes : Int) : Fragment() {
//
//    lateinit var binding : FragmentBannerBinding
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = FragmentBannerBinding.inflate(inflater, container, false)
//
//        binding.bannerImageIv.setImageResource(imgRes)
//
//        return binding.root
//    }
//}

class BannerFragment(val imgRes : Int): BaseFragment<FragmentBannerBinding>(FragmentBannerBinding::inflate)
{
    override fun initAfterBinding() {
        binding.bannerImageIv.setImageResource(imgRes)
    }

}