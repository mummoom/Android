package com.mummoom.md.ui.main

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
//import com.kakao.sdk.common.util.Utility
import com.mummoom.md.R
import com.mummoom.md.databinding.ActivityMainBinding
import com.mummoom.md.ui.BaseActivity

class MainActivity: BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var navHostFragment: NavHostFragment

    override fun initAfterBinding() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.findNavController()

        binding.mainBottomNavigation.setupWithNavController(navController)


    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val keyHash = Utility.getKeyHash(this)
//        Log.d("Hash", keyHash)
//
}