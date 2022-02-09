package com.mummoom.md.ui.doggender

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mummoom.md.R
import com.mummoom.md.data.entities.Dog
import com.mummoom.md.data.remote.Dog.DogService
import com.mummoom.md.databinding.ActivityDoggenderBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogbirth.DogbirthActivity
import com.mummoom.md.ui.dogbreed.DogbreedActivity
import com.mummoom.md.ui.doginfocheck.DogInfoCheckActivity
import com.mummoom.md.ui.main.MainActivity
import com.mummoom.md.ui.main.home.HomeFragment

class DoggenderActivity : BaseActivity<ActivityDoggenderBinding>(ActivityDoggenderBinding::inflate) ,DogInfoView, View.OnClickListener{
    private lateinit var dogInfo : String
    private var dogSex : String = "0"
    private var surgery : String = "Y"
    override fun initAfterBinding() {

        binding.doggenderPreviousBtn.setOnClickListener(this)
        binding.doggenderNextBtn.setOnClickListener(this)
        binding.doggenderMIv.setOnClickListener(this)
        binding.doggenderWIv.setOnClickListener(this)
        binding.doggenderNeuteringCheckOffIv.setOnClickListener(this)
        binding.doggenderNeuteringCheckOnIv.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let {
            it.getStringExtra("dogInfo")?.let{ content->
                dogInfo=content
            }
        }
    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.doggenderPreviousBtn -> startActivityWithClear(DoggenderActivity::class.java)

            binding.doggenderMIv ->
            {
                dogSex="0"
                binding.doggenderMIv.setImageResource(R.drawable.m_check)
                binding.doggenderWIv.setImageResource(R.drawable.w_uncheck)

            }
            binding.doggenderWIv -> {
                dogSex="1"
                binding.doggenderMIv.setImageResource(R.drawable.m_uncheck)
                binding.doggenderWIv.setImageResource(R.drawable.w_check)


            }
            binding.doggenderNeuteringCheckOffIv -> {
                surgery="Y"
                binding.doggenderNeuteringCheckOffIv.visibility=View.INVISIBLE
                binding.doggenderNeuteringCheckOnIv.visibility=View.VISIBLE
            }
            binding.doggenderNeuteringCheckOnIv -> {
                surgery="N"
                binding.doggenderNeuteringCheckOffIv.visibility=View.VISIBLE
                binding.doggenderNeuteringCheckOnIv.visibility=View.INVISIBLE
            }
            binding.doggenderNextBtn->dogInfo()


        }
    }

    private fun getDogInfo() : Dog{
        val token = dogInfo.split(",")
        var dogName : String = token[0]
        var dogBirth : String = token[1]
        var dogType : String = token[2]


        Log.d("DOGINFO_NAME",dogName)
        Log.d("DOGINFO_BIRTH",dogBirth)
        Log.d("DOGINFO_TYPE",dogType)
        Log.d("DOGINFO_SEX",dogSex)
        Log.d("DOGINFO_SURGERY",surgery)

        return Dog(dogBirth, dogIdx = 0,dogName,dogSex,dogType,surgery)

    }

    private fun dogInfo() {

        DogService.dogInfo(this,getDogInfo())

    }

    override fun onDogInfoLoading() {

    }
    override fun onDogInfoSuccess(dogIdx: Dog)
 {
     Log.d("LOG_SUCCESS","성공")
        startActivityWithClear(DogInfoCheckActivity::class.java)
    }

    override fun onDogInfoFailure(code: Int, message: String) {
        when(code) {
            6000 -> {
                Toast.makeText(this, "강아지 정보가 정확하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }


}