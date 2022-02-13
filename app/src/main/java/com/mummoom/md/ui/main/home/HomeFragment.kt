package com.mummoom.md.ui.main.home

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.mummoom.md.R
import com.mummoom.md.databinding.FragmentHomeBinding
import com.mummoom.md.ui.BaseFragment
import java.io.ByteArrayOutputStream


class HomeFragment(): BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    override fun initAfterBinding() {

        //
        val bannerAdapter = HomeBannerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.mummoom_banner_1))
        bannerAdapter.addFragment(BannerFragment(R.drawable.mummoom_banner_2))

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

        binding.homeEatNeverCv.setOnClickListener{
            val intent = Intent(activity, FoodeatActivity::class.java )
            //아이콘 intent로 보내주기
            val bitmap : Bitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_never_black)
            val stream = ByteArrayOutputStream()
            bitmap.compress(CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            intent.putExtra("image",byteArray)
            //내용과 제목 보내주기
            intent.putExtra("level",0)
            intent.putExtra("category","절대 안돼!")

            startActivity(intent)
        }

        binding.homeEatGoodCv.setOnClickListener{
            val intent = Intent(activity, FoodeatActivity::class.java )
            //아이콘 intent로 보내주기
            val bitmap : Bitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_good_black)
            val stream = ByteArrayOutputStream()
            bitmap.compress(CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            intent.putExtra("image",byteArray)
            //내용과 제목 보내주기
            intent.putExtra("level",2)
            intent.putExtra("category","좋은 음식")

            startActivity(intent)
        }

        binding.homeEatSosoCv.setOnClickListener{
            val intent = Intent(activity, FoodeatActivity::class.java )
            //아이콘 intent로 보내주기
            val bitmap : Bitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_soso_black)
            val stream = ByteArrayOutputStream()
            bitmap.compress(CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            intent.putExtra("image",byteArray)
            //내용과 제목 보내주기
            intent.putExtra("level",1)
            intent.putExtra("category","애매한 음식")

            startActivity(intent)
        }

        binding.homeSearchEt.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    val intent = Intent(activity, SearchActivity::class.java)
                    val ingredientName = binding.homeSearchEt.text.toString()
                    intent.putExtra("ingredientName",ingredientName)

                    startActivity(intent)
                    return true
                }
                return false
            }
        })

        //검색 데이터 보내주기
        binding.homeSearchIv.setOnClickListener{

            if(binding.homeSearchEt.length() <= 0){
                Toast.makeText(context, "검색어를 입력해주세요",Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(activity, SearchActivity::class.java)
                val ingredientName = binding.homeSearchEt.text.toString()
                intent.putExtra("ingredientName",ingredientName)

                startActivity(intent)

            }

        }






    }


}