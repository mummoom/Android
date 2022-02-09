package com.mummoom.md.ui.main.community

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.mummoom.md.databinding.FragmentCommunityBinding
import com.mummoom.md.ui.BaseFragment

class CommunityFragment(): BaseFragment<FragmentCommunityBinding>(FragmentCommunityBinding::inflate) {

    override fun initAfterBinding() {

        // gridLayout 설정
        binding.communityWritingRv.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        // 글 쓰기 아이콘 눌렀을 때
        binding.communityWriteBtnCv.setOnClickListener {
            val intent = Intent(activity, WriteActivity::class.java)
            startActivity(intent)
        }
    }
}