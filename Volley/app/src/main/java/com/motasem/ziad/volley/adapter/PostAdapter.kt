package com.motasem.ziad.volley.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.motasem.ziad.volley.R
import com.motasem.ziad.volley.model.Post
import kotlinx.android.synthetic.main.post_item.view.*

class PostAdapter(var context: Context, var list: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvUserId = itemView.tvUserId
        val tvId = itemView.tvId
        val tvTitle = itemView.tvTitle
        val tvBody = itemView.tvBody
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.post_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PostAdapter.MyViewHolder, position: Int) {
        holder.tvUserId.text = list[position].userId.toString()
        holder.tvId.text = list[position].id.toString()
        holder.tvTitle.text = list[position].title
        holder.tvBody.text = list[position].body
    }

}