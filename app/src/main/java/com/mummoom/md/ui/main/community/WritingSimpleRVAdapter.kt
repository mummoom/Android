package com.mummoom.md.ui.main.community

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mummoom.md.R
import com.mummoom.md.data.Post.Post
import com.mummoom.md.databinding.ItemWritingSimpleBinding

class WritingSimpleRVAdapter(val context: Context) : RecyclerView.Adapter<WritingSimpleRVAdapter.ViewHolder>() {

    private val writingList = ArrayList<Post>()
    private lateinit var myItemClickListener : MyItemClickListener

    interface MyItemClickListener{
        fun onItemClick(postIdx : Int)
    }

    fun setMyItemClickListener(itemClickListener: MyItemClickListener)
    {
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WritingSimpleRVAdapter.ViewHolder {
        val binding: ItemWritingSimpleBinding = ItemWritingSimpleBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WritingSimpleRVAdapter.ViewHolder, position: Int) {
        holder.bind(writingList[position])
        holder.itemView.setOnClickListener {
            myItemClickListener.onItemClick(writingList[position].postIdx)
        }
    }

    override fun getItemCount(): Int = writingList.size

    fun addWriting(posts : ArrayList<Post>)
    {
        this.writingList.clear()
        this.writingList.addAll(posts)

        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding : ItemWritingSimpleBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(post : Post)
        {
            // 이미지
            if(post.imgUrl == "" || post.imgUrl == null)
            {
                Glide.with(context)
                    .load(R.drawable.ic_basic_img)
                    .into(binding.simpleWritingImgIv)
            }
            else
            {
                Glide.with(context)
                    .load(post.imgUrl)
                    .into(binding.simpleWritingImgIv)
            }

            // 제목
            binding.simpleWritingTitleTv.text = post.title

            // 내용
            binding.simpleWritingContentTv.text = post.content

            // 좋아요 개수
            binding.simpleLikeCntTv.text = post.likecnt.toString()
        }
    }


}