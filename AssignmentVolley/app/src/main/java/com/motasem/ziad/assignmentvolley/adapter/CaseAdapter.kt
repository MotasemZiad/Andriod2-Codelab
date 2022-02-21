package com.motasem.ziad.assignmentvolley.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.motasem.ziad.assignmentvolley.R
import com.motasem.ziad.assignmentvolley.model.Case
import kotlinx.android.synthetic.main.case_item.view.*

class CaseAdapter(var context: Context, var list: ArrayList<Case>) :
    RecyclerView.Adapter<CaseAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNumber = itemView.tvNumber
        val tvAge = itemView.tvAge
        val tvGender = itemView.tvGender
        val tvLocation = itemView.tvLocation
        val tvDate = itemView.tvDate
        val tvCondition = itemView.tvCondition
        val tvQuarantine = itemView.tvQuarantine
        val tvCommunity = itemView.tvCommunity
        val tvInfection = itemView.tvInfection
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.case_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvNumber.text = list[position].number
        holder.tvAge.text = list[position].age
        holder.tvGender.text = list[position].gender
        holder.tvDate.text = list[position].date
        holder.tvLocation.text = list[position].location
        holder.tvCommunity.text = list[position].community
        holder.tvCondition.text = list[position].condition
        holder.tvInfection.text = list[position].infection
        holder.tvQuarantine.text = list[position].quarantine
    }
}