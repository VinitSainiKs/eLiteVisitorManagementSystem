package com.example.elitevisitormanagementsystem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*


class VisitorAdaptor(val context: Context, val visitorList: ArrayList<VisitorResponse>): RecyclerView.Adapter<VisitorAdaptor.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return visitorList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val visitor = visitorList[position]
        holder.setData(visitor)

    }


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun setData(visitor: VisitorResponse){
            itemView.textview_id.text = visitor.name
        }
    }


}