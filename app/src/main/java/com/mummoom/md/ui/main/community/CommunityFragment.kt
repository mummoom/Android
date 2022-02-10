package com.mummoom.md.ui.main.community

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.mummoom.md.data.Post.Post
import com.mummoom.md.data.remote.Post.GetPostsView
import com.mummoom.md.data.remote.Post.PostService
import com.mummoom.md.databinding.FragmentCommunityBinding
import com.mummoom.md.ui.BaseFragment

class CommunityFragment(): BaseFragment<FragmentCommunityBinding>(FragmentCommunityBinding::inflate), GetPostsView {

    private lateinit var writingSimpleRVAdapter : WritingSimpleRVAdapter

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

    override fun onGetPostsLoading() {
        TODO("Not yet implemented")
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