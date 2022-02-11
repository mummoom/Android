package com.mummoom.md.ui.main.mypage

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mummoom.md.data.Post.Post
import com.mummoom.md.data.remote.Post.GetPostsView
import com.mummoom.md.data.remote.Post.PostService
import com.mummoom.md.databinding.FragmentMylikedBinding
import com.mummoom.md.ui.BaseFragment
import com.mummoom.md.ui.main.community.WritingDetailActivity

class MyLikedFragment() : BaseFragment<FragmentMylikedBinding>(FragmentMylikedBinding::inflate), GetPostsView {

    private lateinit var likedPostRVAdapter : LikedPostRVAdapter

    override fun initAfterBinding() {

    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        getLikedPost()
    }

    private fun getLikedPost()
    {
        val likedPostService = PostService()
        likedPostService.setGetPostsView(this)
        likedPostService.getLiked()
    }

    // recyclerview 초기 세팅
    private fun initRecyclerView()
    {
        likedPostRVAdapter = LikedPostRVAdapter(requireContext())
        binding.myLikedWritingRv.adapter = likedPostRVAdapter
        binding.myLikedWritingRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        likedPostRVAdapter.setMyItemClickListener(object : LikedPostRVAdapter.MyItemClickListener{
            override fun onItemClick(postIdx: Int) {
                val intent = Intent(activity, WritingDetailActivity::class.java)

                intent.putExtra("postIdx", postIdx)
                startActivity(intent)
            }

        })
    }

    // getLikedPost API
    override fun onGetPostsLoading() {
        TODO("Not yet implemented")
    }

    override fun onGetPostsSuccess(posts: ArrayList<Post>) {
        likedPostRVAdapter.addWriting(posts)
    }

    override fun onGetPostsFailure(code: Int, message: String) {
        when(code)
        {
            400 -> Log.d("Like_fail", message)
        }
    }
}
