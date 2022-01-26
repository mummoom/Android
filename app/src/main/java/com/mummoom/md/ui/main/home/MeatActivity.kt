package com.mummoom.md.ui.main.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.mummoom.md.databinding.ActivityMeatBinding
import com.mummoom.md.ui.BaseActivity

class MeatActivity : BaseActivity<ActivityMeatBinding>(ActivityMeatBinding::inflate){

    override fun initAfterBinding() {

        binding.meatInfoRv.layoutManager = LinearLayoutManager(this)
    }
}