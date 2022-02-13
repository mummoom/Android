package com.mummoom.md.ui.main.community

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mummoom.md.R
import com.mummoom.md.data.Post.Comment
import com.mummoom.md.data.Post.Post
import com.mummoom.md.databinding.ItemWritingCommentBinding
import com.mummoom.md.databinding.ItemWritingSimpleBinding

class CommentRVAdapter(val context: Context) : RecyclerView.Adapter<CommentRVAdapter.ViewHolder>() {

    private val commentList = ArrayList<Comment>()
    private lateinit var myItemClickListener : MyItemClickListener

    interface MyItemClickListener{
        fun onItemClick(commentIdx : Int)
    }

    fun setMyItemClickListener(itemClickListener: MyItemClickListener)
    {
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CommentRVAdapter.ViewHolder {
        val binding: ItemWritingCommentBinding = ItemWritingCommentBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentRVAdapter.ViewHolder, position: Int) {
        holder.bind(commentList[position])
        holder.binding.writingCommentMoreBtnIv.setOnClickListener {
            myItemClickListener.onItemClick(commentList[position].commentIdx)
        }
    }

    override fun getItemCount(): Int = commentList.size

    fun addComments(comments : ArrayList<Comment>)
    {
        this.commentList.clear()
        this.commentList.addAll(comments)

        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding : ItemWritingCommentBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(comment : Comment)
        {
            // 제목
            binding.writingCommentUserNameTv.text = comment.nickName
            binding.writingCommentDateTv.text = comment.createAt
            binding.writingCommentContentTv.text = comment.content
        }
    }


}