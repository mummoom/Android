package com.mummoom.md.ui.main.community

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mummoom.md.R
import com.mummoom.md.data.Post.PostDetail
import com.mummoom.md.data.remote.Post.*
import com.mummoom.md.databinding.ActivityWritingdetailBinding
import com.mummoom.md.ui.BaseActivity

class WritingDetailActivity : BaseActivity<ActivityWritingdetailBinding>(ActivityWritingdetailBinding::inflate), GetPostView, LikeView,
    DeletePostView, ReportPostView, WriteCommentView, DeleteCommentView, ReportCommentView {

    private lateinit var newPost : PostDetail
    private lateinit var writeCommentRVAdapter : CommentRVAdapter


    private var postIdx : Int = -1
    private var commentIdx : Int = -1
    private var reason : String = ""
    private var content : String = ""


    override fun initAfterBinding() {

        val moreBtnDialog = WritingMoreBtnDialog(this)
        val reportDialog = ReportDialog(this)


        // 좋아요 버튼
        binding.writingDetailHeartIv.setOnClickListener {
            setLike()
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
                reportDialog.MyDig()
            }
            
            override fun onDeletePost() {
                deletePost()
            }

        })

        reportDialog.setOnClickedListener(object : ReportDialog.clickListener{
            override fun onClicked(reason: String) {
                // reason 값으로 신고 API 호출
                this@WritingDetailActivity.reason = reason
                reportPost()
            }

        })

        // 댓글 전송 버튼
        binding.writingDetailSendIconIv.setOnClickListener {
            content = binding.writingDetailCommentEt.text.toString()
            writeComment()
        }
    }

    private fun initRecyclerView()
    {
        val commentMoreBtnDialog = CommentMoreBtnDialog(this)
        val reportDialog = ReportDialog(this)

        writeCommentRVAdapter = CommentRVAdapter(this)
        binding.writingDetailCommentRv.adapter = writeCommentRVAdapter
        binding.writingDetailCommentRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        writeCommentRVAdapter.setMyItemClickListener(object : CommentRVAdapter.MyItemClickListener{
            override fun onItemClick(commentIdx: Int) {
                // 누르면 삭제 메세지 다이얼로그 뜨게 하고 클릭 리스너에 댓글 삭제 API 호출
                this@WritingDetailActivity.commentIdx = commentIdx
                commentMoreBtnDialog.MyDig()
            }

        })

        // comment dialog의 클릭 리스너
        commentMoreBtnDialog.setOnClickedListener(object : CommentMoreBtnDialog.clickListener{
            override fun onWarningComment() {
                // 댓글 신고 API 함수 호출
                reportDialog.MyDig()
            }

            override fun onDeleteComment() {
                // 댓글 삭제 API 함수 호출
                deleteComment()
            }

        })

        reportDialog.setOnClickedListener(object : ReportDialog.clickListener{
            override fun onClicked(reason: String) {
                this@WritingDetailActivity.reason = reason
                reportComment()
            }

        })

    }

    private fun reportComment()
    {
        val reportCommentService = PostService()
        reportCommentService.setReportCommentView(this)
        reportCommentService.reportComment(commentIdx, reason)
    }

    // 댓글 삭제하는 함수
    private fun deleteComment()
    {
        val deleteCommentService = PostService()
        deleteCommentService.setDeleteCommentView(this)
        deleteCommentService.deleteComment(commentIdx)
    }

    // 댓글 작성하는 함수
    private fun writeComment()
    {
        val writeCommentService = PostService()
        writeCommentService.setWriteCommentView(this)
        writeCommentService.writeComment(postIdx, content)
    }

    // 포스트를 신고하는 함수
    private fun reportPost()
    {
        val reportPostService = PostService()
        reportPostService.setReportView(this)
        reportPostService.reportPost(postIdx, reason)
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

    // 좋아요 버튼 눌리면 좋아요 수 카운팅 해주는 함수
    private fun setHeartNum(isLike: Boolean)
    {
        if(isLike)
        {
            newPost.likecnt++
        }
        else
        {
            newPost.likecnt--
        }
    }

    // 현재 좋아요 상태에 따라 하트 버튼 렌더링 해주는 함수
    private fun setHeartState(isLike: Boolean)
    {
        if(isLike)
        {
            if(newPost.likecnt == 1)
            {
                binding.writingDetailLikeCntTv.visibility = View.VISIBLE
            }
            binding.writingDetailHeartIv.setImageResource(R.drawable.ic_heart_on)
            binding.writingDetailLikeCntTv.text = newPost.likecnt.toString()
        }
        else
        {
            if(newPost.likecnt == 0)
            {
                binding.writingDetailLikeCntTv.visibility = View.GONE
            }
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
        initRecyclerView()
        initWriting()
    }

    // post 눌렀을 때 상세페이지 조회하는 부분
    override fun onGetPostLoading() {
        val animation = AnimationUtils.loadAnimation(this,R.anim.rotate)
        binding.writingDetailRotateIv.visibility = View.VISIBLE
        binding.writingDetailLoadingIv.visibility = View.VISIBLE
        binding.writingDetailRotateIv.startAnimation(animation)
    }

    override fun onGetPostSuccess(post: PostDetail) {
        binding.writingDetailRotateIv.animation.cancel()
        binding.writingDetailRotateIv.visibility = View.GONE
        binding.writingDetailLoadingIv.visibility = View.GONE

        newPost = post
        setView()
    }

    override fun onGetPostFailure(code: Int, message: String) {
        binding.writingDetailRotateIv.animation.cancel()
        binding.writingDetailRotateIv.visibility = View.GONE
        binding.writingDetailLoadingIv.visibility = View.GONE

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

        // 작성 날짜
        binding.writingDetailDateTv.text = newPost.createdAt

        // 댓글창의 유저 아이콘 이미지
//        Glide.with(this)
//            .load(newPost.userImage)
//            .into(binding.writingDetailCommentUserIconIv)

        // 유저 닉네임
        binding.writingDetailUserNicknameTv.text = newPost.userName

        // 글 사진
        if(newPost.imgUrl == "" || newPost.imgUrl == null)
        {
            Glide.with(this)
                .load(R.drawable.ic_basic_img)
                .into(binding.writingDetailWritingImgIv)
        }
        else
        {
            Glide.with(this)
                .load(newPost.imgUrl)
                .into(binding.writingDetailWritingImgIv)
        }


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

        // 댓글 recyclerview 세팅
        writeCommentRVAdapter.addComments(newPost.comments)
        if(newPost.comments.size > 0)
        {
            binding.writingDetailCommentRv.visibility = View.VISIBLE
        }
        else
        {
            binding.writingDetailCommentRv.visibility = View.GONE
        }
    }

    // setLike API 부분
    override fun onLikeLoading() {

    }

    override fun onLikeSuccess(isLike: Boolean) {
        newPost.like = isLike
        setHeartNum(newPost.like)
        setHeartState(newPost.like)
    }

    override fun onLikeFailure(code: Int, message: String) {
        when(code)
        {
            400 -> Log.d("Like_fail", message)
        }
    }

    // deletePost API 부분
    override fun onDeleteLoading() {

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
            else -> showToast("오류가 발생하였습니다.")
        }
    }

    // reportPost API 부분
    override fun onReportPostLoading() {

    }

    override fun onReportPostSuccess() {
        showToast("신고가 완료되었습니다.")
        finish()
    }

    override fun onReportPostFailure(code: Int, message: String) {
        when(code)
        {
            3000 -> showToast("오류가 발생하였습니다.")
            8000 -> showToast("존재하지 않는 게시글 입니다.")
            8001 -> showToast("회원정보를 찾을 수 없습니다.")
            else -> showToast("오류가 발생하였습니다.")
        }
    }

    // writeComment API 부분
    override fun onWriteCommentLoading() {
        val animation = AnimationUtils.loadAnimation(this,R.anim.rotate)
        binding.writingDetailRotateIv.visibility = View.VISIBLE
        binding.writingDetailLoadingIv.visibility = View.VISIBLE
        binding.writingDetailRotateIv.startAnimation(animation)
    }

    override fun onWriteCommentSuccess() {
        binding.writingDetailRotateIv.animation.cancel()
        binding.writingDetailRotateIv.visibility = View.GONE
        binding.writingDetailLoadingIv.visibility = View.GONE
        binding.writingDetailCommentEt.text = null
        initWriting()
    }

    override fun onWriteCommentFailure(code: Int, message: String) {
        binding.writingDetailRotateIv.animation.cancel()
        binding.writingDetailRotateIv.visibility = View.GONE
        binding.writingDetailLoadingIv.visibility = View.GONE
        when(code)
        {
            3000 -> showToast("오류가 발생하였습니다.")
            8000 -> showToast("존재하지 않는 게시글 입니다.")
            8001 -> showToast("회원정보를 찾을 수 없습니다.")
            8006 -> showToast("내용을 입력해주세요.")
            else -> showToast("오류가 발생하였습니다.")
        }
    }

    // deleteComment API 부분
    override fun onDeleteCommentLoading() {
        val animation = AnimationUtils.loadAnimation(this,R.anim.rotate)
        binding.writingDetailRotateIv.visibility = View.VISIBLE
        binding.writingDetailLoadingIv.visibility = View.VISIBLE
        binding.writingDetailRotateIv.startAnimation(animation)
    }

    override fun onDeleteCommentSuccess() {
        binding.writingDetailRotateIv.animation.cancel()
        binding.writingDetailRotateIv.visibility = View.GONE
        binding.writingDetailLoadingIv.visibility = View.GONE

        showToast("삭제가 완료되었습니다.")
        initWriting()
    }

    override fun onDeleteCommentFailure(code: Int, message: String) {
        binding.writingDetailRotateIv.animation.cancel()
        binding.writingDetailRotateIv.visibility = View.GONE
        binding.writingDetailLoadingIv.visibility = View.GONE

        when(code)
        {
            8001 -> showToast("회원정보를 찾을 수 없습니다.")
            8002 -> showToast("존재하지 않는 댓글 입니다.")
            8004 -> showToast("작성자만 삭제할 수 있습니다.")
            else -> showToast("오류가 발생하였습니다.")
        }
    }

    // reportComment API 부분
    override fun onReportCommentLoading() {

    }

    override fun onReportCommentSuccess() {
        showToast("신고가 완료되었습니다.")
        finish()
    }

    override fun onReportCommentFailure(code: Int, message: String) {
        when(code)
        {
            3000 -> showToast("오류가 발생하였습니다.")
            8001 -> showToast("회원정보를 찾을 수 없습니다.")
            8002 -> showToast("존재하지 않는 댓글 입니다.")
            else -> showToast("오류가 발생하였습니다.")
        }
    }


}