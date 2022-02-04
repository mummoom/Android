package com.mummoom.md.ui.doggender

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mummoom.md.R
import com.mummoom.md.data.entities.Dog
import com.mummoom.md.databinding.ActivityDoggenderBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogbirth.DogbirthActivity
import com.mummoom.md.ui.dogbreed.DogbreedActivity
import com.mummoom.md.ui.doginfocheck.DogInfoCheckActivity

class DoggenderActivity : BaseActivity<ActivityDoggenderBinding>(ActivityDoggenderBinding::inflate) ,DogInfoView, View.OnClickListener{
    private lateinit var dogInfo : String
    private lateinit var dogSex : String
    private lateinit var surgery : String
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
            binding.doggenderNextBtn -> startActivityWithClear(DoggenderActivity::class.java)
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


        }
    }

    private fun getDogInfo() {
        val token = dogInfo.split(",")
        val dogName : String = token[0]
        val dogBirth : String = token[1]
        val dogType : String = token[2]


        //return Dog(dogBirth,dogName,dogSex,dogType,surgery,userIdx)

    }

    override fun onDogInfoLoading() {
        TODO("Not yet implemented")
    }

    override fun onDogInfoSuccess() {
        TODO("Not yet implemented")
    }

    override fun onDogInfoFailure(code: Int, message: String) {
        TODO("Not yet implemented")
    }


}