package com.mummoom.md.ui.main.mypage

import com.google.android.material.tabs.TabLayoutMediator
import com.mummoom.md.databinding.ActivityMyactiveBinding
import com.mummoom.md.ui.BaseActivity

class MyActiveActivity : BaseActivity<ActivityMyactiveBinding>(ActivityMyactiveBinding::inflate){

    val tabInfo = arrayListOf("내가 쓴 게시글", "좋아요 한 글")

    override fun initAfterBinding() {

        // tabLayout과 viewpager 연동 과정
        val myActiveAdapter = MyActiveViewpagerAdapter(this)
        binding.myactiveContentVp.adapter = myActiveAdapter

        TabLayoutMediator(binding.myactiveContentTb, binding.myactiveContentVp)
        {
            tab, position ->
            tab.text = tabInfo[position]
        }.attach()

        binding.myactiveBackarrowBtn.setOnClickListener {
            finish()
        }
    }

}