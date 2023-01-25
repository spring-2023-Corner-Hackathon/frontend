package com.example.photobook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.photobook.databinding.ActivityMusicCategoryBinding

class MusicCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_music_category)

        var musicCategory = "null"
        var musicCategoryKorean = "null"
        val binding = ActivityMusicCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.musicCategory1.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.musicCategory2.isChecked = false
                binding.musicCategory3.isChecked = false
                binding.musicCategory4.isChecked = false
                binding.musicCategory5.isChecked = false
                binding.musicCategory6.isChecked = false
                musicCategory = "travel"
                musicCategoryKorean = "여행"
            }
        }
        binding.musicCategory2.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.musicCategory1.isChecked = false
                binding.musicCategory3.isChecked = false
                binding.musicCategory4.isChecked = false
                binding.musicCategory5.isChecked = false
                binding.musicCategory6.isChecked = false
                musicCategory = "tong"
                musicCategoryKorean = "통통튀는"
            }
        }
        binding.musicCategory3.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.musicCategory1.isChecked = false
                binding.musicCategory2.isChecked = false
                binding.musicCategory4.isChecked = false
                binding.musicCategory5.isChecked = false
                binding.musicCategory6.isChecked = false
                musicCategory = "refined"
                musicCategoryKorean = "세련된"
            }
        }
        binding.musicCategory4.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.musicCategory1.isChecked = false
                binding.musicCategory2.isChecked = false
                binding.musicCategory3.isChecked = false
                binding.musicCategory5.isChecked = false
                binding.musicCategory6.isChecked = false
                musicCategory = "calm"
                musicCategoryKorean = "잔잔한"
            }
        }
        binding.musicCategory5.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.musicCategory1.isChecked = false
                binding.musicCategory2.isChecked = false
                binding.musicCategory3.isChecked = false
                binding.musicCategory4.isChecked = false
                binding.musicCategory6.isChecked = false
                musicCategory = "sad"
                musicCategoryKorean = "슬픈"
            }
        }
        binding.musicCategory6.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.musicCategory1.isChecked = false
                binding.musicCategory2.isChecked = false
                binding.musicCategory3.isChecked = false
                binding.musicCategory4.isChecked = false
                binding.musicCategory5.isChecked = false
                musicCategory = "pop"
                musicCategoryKorean = "팝송"
            }
        }

        binding.coverNext.setOnClickListener {
            if(!binding.musicCategory1.isChecked && !binding.musicCategory2.isChecked &&
                !binding.musicCategory3.isChecked && !binding.musicCategory4.isChecked &&
                !binding.musicCategory5.isChecked && !binding.musicCategory6.isChecked) {
                Toast.makeText(this, "카테고리를 한 가지 선택해주세요", Toast.LENGTH_SHORT)
            }
            else {
                val intent = Intent(this, ChooseMusic::class.java)
                intent.putExtra("musicCategory", musicCategoryKorean)
                startActivity(intent)
            }
        }


        binding.coverBack.setOnClickListener {
            val intent = Intent(this, MakeActivity::class.java)
            startActivity(intent)
        }
    }
}