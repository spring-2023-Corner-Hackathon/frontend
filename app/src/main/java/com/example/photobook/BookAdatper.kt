package com.example.photobook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.photobook.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.example.photobook.databinding.ItemMainBinding
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent

lateinit var binding: ItemMainBinding

class BookAdatper(private val context: Context) : RecyclerView.Adapter<BookAdatper.ViewHolder>() {

    var datas = mutableListOf<BookData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_main,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.pt_title)
        private val imgProfile: ImageView = itemView.findViewById(R.id.pt_imageBtn)

        fun bind(item: BookData) {
            txtName.text = item.title
            Glide.with(itemView).load(item.img).into(imgProfile)
        }
    }


}