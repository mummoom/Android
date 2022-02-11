package com.mummoom.md.ui.main.community

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.mummoom.md.R
import com.mummoom.md.data.Post.PostDetail
import com.mummoom.md.data.remote.Post.DeletePostView
import com.mummoom.md.data.remote.Post.GetPostView
import com.mummoom.md.data.remote.Post.LikeView
import com.mummoom.md.data.remote.Post.PostService
import com.mummoom.md.databinding.ActivityWritingdetailBinding
import com.mummoom.md.ui.BaseActivity

class WritingDetailActivity : BaseActivity<ActivityWritingdetailBinding>(ActivityWritingdetailBinding::inflate), GetPostView, LikeView, DeletePostView {

    private lateinit var newPost : PostDetail

    private var postIdx : Int = -1
    private var isScrap = false

    override fun initAfterBinding() {

        val moreBtnDialog = WritingMoreBtnDialog(this)

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

        // 더보기 버튼
        binding.writingDetailMoreBtnIv.setOnClickListener {
            moreBtnDialog.MyDig()
        }

        moreBtnDialog.setOnClickedListener(object : WritingMoreBtnDialog.clickListener{
            override fun onReportPost() {
                // 신고 API
            }

            override fun onDeletePost() {
                deletePost()
            }

        })

    }

    // 포스트를 삭제하는 함수
    private fun deletePost()
    {
        val deletePostService = PostService()
        deletePostService.setDeleteView(this)
        deletePostService.deletePost(postIdx)
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
            binding.writingDetailLikeCntTv.text = newPost.likecnt.toString()
        }
        else
        {
            binding.writingDetailHeartIv.setImageResource(R.drawable.ic_heart_off)
            binding.writingDetailLikeCntTv.text = newPost.likecnt.toString()
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


        // 글 사진
        Glide.with(this)
            .load(newPost.imgUrl)
            .into(binding.writingDetailWritingImgIv)

        // 글 제목
        binding.writingDetailWritingTitleTv.text = newPost.title

        // 글 내용
        binding.writingDetailWritingContentTv.text = newPost.content

        // 좋아요 개수
        if(newPost.likecnt > 0)
        {
            binding.writingDetailLikeCntTv.visibility = View.VISIBLE
            binding.writingDetailLikeCntTv.text = newPost.likecnt.toString()
        }
        else
        {
            binding.writingDetailLikeCntTv.visibility = View.GONE
        }

        // 좋아요 하트 버튼 세팅
        setHeartState(newPost.like)

        // 댓글 개수 세팅
        binding.writingDetailCommentCntTv.text = "댓글 " + newPost.comments.size


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

    override fun onDeleteLoading() {
        TODO("Not yet implemented")
    }

    override fun onDeleteSuccess() {
        showToast("게시글 삭제가 완료되었습니다.")
        finish()
    }

    override fun onDeleteFailure(code: Int, message: String) {
        when(code)
        {
            8000 -> showToast("존재하지 않는 게시글 입니다.")
            8001 -> showToast("회원정보를 찾을 수 없습니다.")
            8004 -> showToast("작성자만 삭제할 수 있습니다.")
            else -> showToast("오류가 발생했습니다.")
        }
    }


}