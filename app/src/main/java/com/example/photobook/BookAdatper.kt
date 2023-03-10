package com.example.photobook

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class BookAdatper(private val context: Context) : RecyclerView.Adapter<BookAdatper.ViewHolder>() {


    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null


    var datas = mutableListOf<BookData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_main,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(itemClick != null){
            holder.itemView.setOnClickListener{ v ->
                itemClick?.onClick(v, position)
            }
        }
        holder.bind(datas[position])

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.pt_title)
        private val imgProfile: ImageView = itemView.findViewById(R.id.pt_imageBtn)
        private val isopen: CheckBox = itemView.findViewById(R.id.pt_isopen)

        fun bind(item: BookData) {
            txtName.text = item.title
            isopen.isChecked = item.isOpen
            Glide.with(itemView).load(item.img).into(imgProfile)
        }
    }

}