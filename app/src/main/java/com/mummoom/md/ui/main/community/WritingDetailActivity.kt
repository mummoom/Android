package com.mummoom.md.ui.main.community

import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.mummoom.md.R
import com.mummoom.md.data.Post.PostDetail
import com.mummoom.md.data.remote.Post.GetPostView
import com.mummoom.md.data.remote.Post.PostService
import com.mummoom.md.databinding.ActivityWritingdetailBinding
import com.mummoom.md.ui.BaseActivity

class WritingDetailActivity : BaseActivity<ActivityWritingdetailBinding>(ActivityWritingdetailBinding::inflate), GetPostView {

    private var postIdx : Int = -1
    private var isLike = false
    private var isScrap = false

    override fun initAfterBinding() {


        // 좋아요 버튼
        binding.writingDetailHeartIv.setOnClickListener {

            if(!isLike)
            {
                binding.writingDetailHeartIv.setImageResource(R.drawable.ic_heart_on)
                isLike = !isLike
            }
            else
            {
                binding.writingDetailHeartIv.setImageResource(R.drawable.ic_heart_off)
                isLike = !isLike
            }
        }

        // 스크랩 버튼
        binding.writingDetailScrapIv.setOnClickListener {

            if(!isScrap)
            {
                binding.writingDetailScrapIv.setImageResource(R.drawable.ic_scrap_on)
                isScrap = !isScrap
            }
            else
            {
                binding.writingDetailScrapIv.setImageResource(R.drawable.ic_scrap)
                isScrap = !isScrap
            }
        }

        // 뒤로가기 버튼
        binding.writingDetailArrowIv.setOnClickListener {
            finish()
        }

    }

    private fun initWriting()
    {
        val getPostService = PostService()
        getPostService.setGetPostView(this)
        getPostService.getPost(postIdx)
    }

    private fun getPostIdx()
    {
        if(intent.hasExtra("postIdx"))
        {
            postIdx = intent.getIntExtra("postIdx", -1)
        }
        Log.d("postIdx", postIdx.toString())
    }

    override fun onStart() {
        super.onStart()

        getPostIdx()
        initWriting()
    }

    override fun onGetPostLoading() {
        TODO("Not yet implemented")
    }

    override fun onGetPostSuccess(post: PostDetail) {

        Log.d("post_response", post.toString())
        // 유저 아이콘 이미지
        Glide.with(this)
            .load(post.userImage)
            .into(binding.writingDetailUserIconIv)

        // 유저 닉네임
        binding.writingDetailUserNicknameTv.text = post.userName

        // 글 제목
        binding.writingDetailWritingTitleTv.text = post.title

        Log.d("post_url", post.imgUrl.toString())

        // 글 사진
        Glide.with(this)
            .load(post.imgUrl)
            .into(binding.writingDetailWritingImgIv)

        // 글 내용
        binding.writingDetailWritingContentTv.text = post.content

    }

    override fun onGetPostFailure(code: Int, message: String) {
        when(code)
        {
            400 -> Log.d("fail", message)
        }
    }


}