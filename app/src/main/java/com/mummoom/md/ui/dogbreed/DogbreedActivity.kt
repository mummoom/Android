package com.mummoom.md.ui.dogbreed

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.mummoom.md.R
import com.mummoom.md.databinding.ActivityDogbreedBinding
import com.mummoom.md.databinding.ActivityDoggenderBinding
import com.mummoom.md.databinding.ActivityDognameBinding
import com.mummoom.md.databinding.ActivityNicknameBinding
import com.mummoom.md.ui.BaseActivity

class DogbreedActivity : BaseActivity<ActivityDogbreedBinding>(ActivityDogbreedBinding::inflate) {
    override fun initAfterBinding() {


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        breedSpinner()
    }
    private fun breedSpinner() {
        binding.dogbreedBreadSp.adapter=
            ArrayAdapter.createFromResource(this, R.array.breed,android.R.layout.simple_spinner_dropdown_item)

        binding.dogbreedBreadSp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

}