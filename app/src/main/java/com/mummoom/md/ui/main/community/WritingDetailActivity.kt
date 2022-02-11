package com.mummoom.md.ui.main.community

import android.util.Log
import com.bumptech.glide.Glide
import com.mummoom.md.R
import com.mummoom.md.data.Post.PostDetail
import com.mummoom.md.data.remote.Post.GetPostView
import com.mummoom.md.data.remote.Post.LikeView
import com.mummoom.md.data.remote.Post.PostService
import com.mummoom.md.databinding.ActivityWritingdetailBinding
import com.mummoom.md.ui.BaseActivity

class WritingDetailActivity : BaseActivity<ActivityWritingdetailBinding>(ActivityWritingdetailBinding::inflate), GetPostView, LikeView {

    private lateinit var newPost : PostDetail

    private var postIdx : Int = -1
    private var isScrap = false

    override fun initAfterBinding() {


        // 좋아요 버튼
        binding.writingDetailHeartIv.setOnClickListener {
            setLike()
            setHeartNum(newPost.like)
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

    // 좋아요 버튼을 눌렀을 때 실행되는 함수
    private fun setLike()
    {
        val setLikeService = PostService()
        setLikeService.setLikeView(this)
        setLikeService.setLike(postIdx)
    }

    private fun setHeartNum(isLike: Boolean)
    {
        if(isLike)
        {
            newPost.likecnt--
        }
        else
        {
            newPost.likecnt++
        }
    }

    private fun setHeartState(isLike: Boolean)
    {
        if(isLike)
        {
            binding.writingDetailHeartIv.setImageResource(R.drawable.ic_heart_on)
            binding.writingDetailLikeCntTv.text = "좋아요 " + newPost.likecnt + "개"
        }
        else
        {
            binding.writingDetailHeartIv.setImageResource(R.drawable.ic_heart_off)
            binding.writingDetailLikeCntTv.text = "좋아요 " + newPost.likecnt + "개"
        }
    }

    // 게시글 상세페이지를 API를 통해 데이터 렌더링 하는 함수
    private fun initWriting()
    {
        val getPostService = PostService()
        getPostService.setGetPostView(this)
        getPostService.getPost(postIdx)
    }

    // intent를 통해 postIdx를 받아오는 함수
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

    // post 눌렀을 때 상세페이지 조회하는 부분
    override fun onGetPostLoading() {
        TODO("Not yet implemented")
    }

    override fun onGetPostSuccess(post: PostDetail) {
        newPost = post
        setView()
    }

    override fun onGetPostFailure(code: Int, message: String) {
        when(code)
        {
            400 -> Log.d("getPost_fail", message)
        }
    }

    // 받은 newPost를 통해서 view 렌더링하는 함수
    private fun setView()
    {
        // 유저 아이콘 이미지
        Glide.with(this)
            .load(newPost.userImage)
            .into(binding.writingDetailUserIconIv)

        // 유저 닉네임
        binding.writingDetailUserNicknameTv.text = newPost.userName

        // 글 제목
        binding.writingDetailWritingTitleTv.text = newPost.title


        // 글 사진
        Glide.with(this)
            .load(newPost.imgUrl)
            .into(binding.writingDetailWritingImgIv)

        // 글 내용
        binding.writingDetailWritingContentTv.text = newPost.content

        // 좋아요 개수
        binding.writingDetailLikeCntTv.text = "좋아요 " + newPost.likecnt + "개"

        // 좋아요 하트 버튼 세팅
        setHeartState(newPost.like)
    }

    // setLike API 부분
    override fun onLikeLoading() {
        TODO("Not yet implemented")
    }

    override fun onLikeSuccess(isLike: Boolean) {
        newPost.like = isLike
        setHeartState(newPost.like)
    }

    override fun onLikeFailure(code: Int, message: String) {
        when(code)
        {
            400 -> Log.d("Like_fail", message)
        }
    }


}