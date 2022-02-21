package com.motasem.ziad.volley.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.motasem.ziad.volley.R
import com.motasem.ziad.volley.model.Movies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.post_item.view.tvTitle

class MoviesAdapter(var context: Context, var list: ArrayList<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgMovie: ImageView = itemView.imgMovie
        val tvTitle: TextView = itemView.tvTitle
        val tvReleaseYear: TextView = itemView.tvReleaseYear
        val tvRating: TextView = itemView.tvRating
        val tvGenre: TextView = itemView.tvGenre
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvRating.text = list[position].rating.toString()
        holder.tvReleaseYear.text = list[position].releaseYear.toString()
        holder.tvTitle.text = list[position].title
        holder.tvGenre.text = list[position].genre
        Glide.with(context).load(list[position].image).into(holder.imgMovie)
    }

}