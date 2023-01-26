package com.example.photobook

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MultiImageAdapter(private val items: ArrayList<Uri>, val context: Context) :
    RecyclerView.Adapter<MultiImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MultiImageAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MultiImageAdapter.ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(context).load(item)
            .into(holder.image)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        var image = v.findViewById<ImageView>(R.id.ImageViewItem)

        fun bind(listener:View.OnClickListener, item:String) {
            view.setOnClickListener(listener)
        }
    }


}