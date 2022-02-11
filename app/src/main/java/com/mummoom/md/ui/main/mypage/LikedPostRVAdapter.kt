package com.mummoom.md.ui.main.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mummoom.md.R
import com.mummoom.md.data.Post.Post
import com.mummoom.md.databinding.ItemWritingBinding

class LikedPostRVAdapter(val context: Context) : RecyclerView.Adapter<LikedPostRVAdapter.ViewHolder>() {

    private val writingList = ArrayList<Post>()
    private lateinit var myItemClickListener : MyItemClickListener

    interface MyItemClickListener{
        fun onItemClick(postIdx : Int)
    }

    fun setMyItemClickListener(itemClickListener: MyItemClickListener)
    {
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LikedPostRVAdapter.ViewHolder {
        val binding: ItemWritingBinding = ItemWritingBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikedPostRVAdapter.ViewHolder, position: Int) {
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

    inner class ViewHolder(val binding : ItemWritingBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(post : Post)
        {
            // 이미지
            if(post.imgUrl == "" || post.imgUrl == null)
            {
                Glide.with(context)
                    .load(R.drawable.ic_basic_img)
                    .into(binding.writingImgIv)
            }
            else
            {
                Glide.with(context)
                    .load(post.imgUrl)
                    .into(binding.writingImgIv)
            }


            // 제목
            binding.writingTitleTv.text = post.title

            // 글 내용
            binding.writingContentTv.text = post.content
        }
    }


}