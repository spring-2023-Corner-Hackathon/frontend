package com.example.photobook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photobook.databinding.ActivityParticipantBinding

class ParticipantActivity : AppCompatActivity() {
    lateinit var binding: ActivityParticipantBinding
    lateinit var recyclerView: RecyclerView
    lateinit var profileAdapter : ProfileAdapter
    val datas = ArrayList<ProfileData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()

        //닉네임 검색하기
        binding.searchBtn.setOnClickListener{
            val getName = binding.nickEdit.getText().toString()

            if(getName.length == 0){
                Toast.makeText(this@ParticipantActivity, "닉네임을 입력하시오.", Toast.LENGTH_SHORT).show()
            }
            else{
                binding.getProfile.visibility = View.VISIBLE
                binding.profileNick.text = getName
                //프로필 사진 변경

                //추가하기 버튼 누르기
                binding.addBtn.setOnClickListener {
                    //참여자 추가하기
                    //getName인 id를 찾아서 공동 수정하기
                    //if(getName == id)
                }
            }
        }


    }

    private fun initRecycler() {
        recyclerView = binding.participantRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this@ParticipantActivity)

        profileAdapter = ProfileAdapter(this@ParticipantActivity)
        binding.participantRecyclerView.adapter = profileAdapter

        profileAdapter.itemClick = object :  ProfileAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

                val checked: CheckBox = view.findViewById(R.id.checked)

                Toast.makeText(this@ParticipantActivity, datas[position].checked.toString(), Toast.LENGTH_SHORT).show()

                //삭제하기 버튼 누르기기
                binding.deleteBtn.setOnClickListener {
                    //선택된 프로필 모두 삭제됨
                }
            }
        }

        datas.apply {
            add(ProfileData(img = R.drawable.bookcover1, nickname = "a" , checked = false))
            add(ProfileData(img = R.drawable.ic_mypage,  nickname = "d" , checked = false))
            add(ProfileData(img = R.drawable.ic_calender, nickname = "c" , checked = false))
            add(ProfileData(img = R.drawable.ic_mypage, nickname = "f" , checked = false))
            add(ProfileData(img = R.drawable.ic_calender, nickname = "e" , checked = true))

            profileAdapter.datas = datas
            profileAdapter.notifyDataSetChanged()
        }
    }
}