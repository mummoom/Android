package com.mummoom.md.ui.main.mypage

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mummoom.md.data.Post.Post
import com.mummoom.md.data.remote.Post.GetPostsView
import com.mummoom.md.data.remote.Post.PostService
import com.mummoom.md.databinding.FragmentMywritingBinding
import com.mummoom.md.ui.BaseFragment
import com.mummoom.md.ui.main.community.WritingDetailActivity

class MyWringFragment() : BaseFragment<FragmentMywritingBinding>(FragmentMywritingBinding::inflate), GetPostsView {

    private lateinit var myWritingRVAdapter : LikedPostRVAdapter

    override fun initAfterBinding() {

    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        getMyWriting()
    }

    private fun getMyWriting()
    {
        val myPostService = PostService()
        myPostService.setGetPostsView(this)
        myPostService.getMyPost()
    }

    // recyclerview 초기 세팅
    private fun initRecyclerView()
    {
        myWritingRVAdapter = LikedPostRVAdapter(requireContext())
        binding.mycommunityWritingRv.adapter = myWritingRVAdapter
        binding.mycommunityWritingRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        myWritingRVAdapter.setMyItemClickListener(object : LikedPostRVAdapter.MyItemClickListener{
            override fun onItemClick(postIdx: Int) {
                val intent = Intent(activity, WritingDetailActivity::class.java)

                intent.putExtra("postIdx", postIdx)
                startActivity(intent)
            }

        })
    }

    // getMyPost API
    override fun onGetPostsLoading() {
        TODO("Not yet implemented")
    }

    override fun onGetPostsSuccess(posts: ArrayList<Post>) {
        myWritingRVAdapter.addWriting(posts)
    }

    override fun onGetPostsFailure(code: Int, message: String) {
        when(code)
        {
            400 -> Log.d("Like_fail", message)
        }
    }
}