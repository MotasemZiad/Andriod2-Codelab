package com.motasem.ziad.assignmentlab6.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.motasem.ziad.assignmentlab6.R
import com.motasem.ziad.assignmentlab6.model.ToDo
import com.motasem.ziad.assignmentlab6.model.ToDoData
import kotlinx.android.synthetic.main.todo_item.view.*

class ToDoAdapter(var context: Context, var list: ArrayList<ToDoData>) :
    RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbIsCompleted: CheckBox = itemView.cbIsCompleted
        val tvTitle: TextView = itemView.tv_title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = list[position].title
        holder.cbIsCompleted.isChecked = list[position].completed
    }
}