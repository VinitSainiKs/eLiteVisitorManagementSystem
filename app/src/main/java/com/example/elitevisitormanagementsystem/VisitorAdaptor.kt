package com.example.elitevisitormanagementsystem

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
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
        val imageAsBytes = Base64.decode(visitor.imageProfile.split(",")[1].toByteArray(), Base64.DEFAULT)
        holder.imageview.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size))


        holder.cardLayout.setOnClickListener {

            val i = Intent(context, VisitiorInfoActivity::class.java)
            i.putExtra("response", visitor)
            context.startActivity(i)

        }

    }


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val cardLayout : CardView = itemView.cardLayout
        val imageview : ImageView = itemView.imageview_id


        fun setData(visitor: VisitorResponse){
            itemView.textview_id.text = visitor.name
        }
    }


}