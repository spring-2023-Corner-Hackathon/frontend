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

class ProfileAdapter(private val context: Context): RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {


    interface ItemClick {
        fun onClick(view: View, position:Int)
    }
    var itemClick: ItemClick? = null

    var datas = mutableListOf<ProfileData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.nick_item, parent,false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int){

        if(itemClick != null){
            holder.itemView.setOnClickListener{ v ->
                itemClick?.onClick(v, position)

            }
        }
        holder.setChecked(datas[position])
        holder.bind(datas[position])
    }

    inner class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val txtNickname: TextView = itemView.findViewById(R.id.profileNick)
        private val imgProfile: ImageView = itemView.findViewById(R.id.profileImg)
        private val checked: CheckBox = itemView.findViewById(R.id.checked)

        //체크버튼 눌렀을때
        fun setChecked(item: ProfileData){
            checked.setOnClickListener {
                if(checked.isChecked == true) {
                    checked.isChecked = true
                    item.checked = true
                } else if(checked.isChecked == false){
                    checked.isChecked = false
                    item.checked = false
                }
            }
        }

        fun bind(item: ProfileData){
            txtNickname.text = item.nickname
            checked.isChecked = item.checked
            Glide.with(itemView).load(item.img).into(imgProfile)
        }
    }


}
