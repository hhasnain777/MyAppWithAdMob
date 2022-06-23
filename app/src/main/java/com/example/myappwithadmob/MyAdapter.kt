package com.example.myappwithadmob

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.GnssAntennaInfo
import android.net.Uri
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myappwithadmob.R.*
import com.google.android.gms.ads.AdView

class mAdapter(private val context: Context, val mlistData: List<DataClass>): RecyclerView.Adapter<mAdapter.mViewHolder>() {
var onItemClick: ((DataClass) -> Unit)? = null
    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout.itemview , parent, false)
        return mViewHolder(view )
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val data= mlistData[position]
       holder.ivImage.setImageResource(data.Image)
        holder.ivTitle.text = data.Title
        holder.itemView.setOnClickListener { onItemClick?.invoke(data) }}

    override fun getItemCount(): Int {
        return mlistData.size
    }
    class mViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivTitle = itemView.findViewById<TextView>(R.id.ivTitle)
        val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)


    }
}
