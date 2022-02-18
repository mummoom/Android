package com.mummoom.md.ui.main.community

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.mummoom.md.R
import com.mummoom.md.data.Post.Post
import com.mummoom.md.data.remote.Post.GetPostsView
import com.mummoom.md.data.remote.Post.PostService
import com.mummoom.md.databinding.FragmentCommunityBinding
import com.mummoom.md.ui.BaseFragment

class CommunityFragment(): BaseFragment<FragmentCommunityBinding>(FragmentCommunityBinding::inflate), GetPostsView {

    private lateinit var writingSimpleRVAdapter : WritingSimpleRVAdapter
    private var recyclerViewState : Parcelable? = null

    override fun initAfterBinding() {

        // 글 쓰기 아이콘 눌렀을 때
        binding.communityWriteBtnCv.setOnClickListener {
            val intent = Intent(activity, WriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView()
    {
        writingSimpleRVAdapter = WritingSimpleRVAdapter(requireContext())
        binding.communityWritingRv.adapter = writingSimpleRVAdapter
        binding.communityWritingRv.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        writingSimpleRVAdapter.setMyItemClickListener(object : WritingSimpleRVAdapter.MyItemClickListener{
            override fun onItemClick(postIdx: Int) {
                val intent = Intent(activity, WritingDetailActivity::class.java)
                intent.putExtra("postIdx", postIdx)
                startActivity(intent)
            }

        })
    }

    // 전체 게시글 피드를 조회하는 함수
    private fun getPosts()
    {
        val writingSimpleService = PostService()
        writingSimpleService.setGetPostsView(this)
        writingSimpleService.getPosts()
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        getPosts()
    }

    override fun onResume() {
        super.onResume()

        getPosts()
    }


    // 다른 activity나 fragment로 이동하기 전에 호출해야 하는 함수 => 프래그먼트에서는 동작하지 않는 애랍니다...
//    private fun saveRecyclerViewState()
//    {
//        recyclerViewState = binding.communityWritingRv.layoutManager!!.onSaveInstanceState()
//    }
//
//    private fun setSavedRecyclerViewState()
//    {
//        binding.communityWritingRv.layoutManager!!.onRestoreInstanceState(recyclerViewState)
//    }

    // 전체 게시글 피드를 불러오는 함수
    override fun onGetPostsLoading() {

    }

    override fun onGetPostsSuccess(posts: ArrayList<Post>) {
        writingSimpleRVAdapter.addWriting(posts)
        Log.d("posts", posts.toString())
    }

    override fun onGetPostsFailure(code: Int, message: String) {
        when(code)
        {
            400 -> Log.d("fail", message)
        }
    }
}