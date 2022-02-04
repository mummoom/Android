package com.mummoom.md.ui.main.mypage

import com.mummoom.md.databinding.FragmentBackgroundBinding
import com.mummoom.md.ui.BaseFragment

class BackgroundFragment(val imgRes : Int): BaseFragment<FragmentBackgroundBinding>(FragmentBackgroundBinding::inflate) {

    override fun initAfterBinding() {
        binding.backgroundBgImgIv.setImageResource(imgRes)
    }
}