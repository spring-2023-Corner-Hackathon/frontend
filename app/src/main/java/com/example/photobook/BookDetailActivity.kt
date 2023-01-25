package com.example.photobook

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.photobook.databinding.ActivityBookDetailBinding


class BookDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityBookDetailBinding
    private val VIDEO_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.videoTitle.text = intent.getStringExtra("title")

        val videoView = binding.videoView
        var uri: Uri = Uri.parse(VIDEO_URL)

        videoView.setVideoURI(uri) // 없으면 에러
        videoView.requestFocus()    // 준비하는 과정을 미리함

        videoView.setOnPreparedListener {
            videoView.start()       // 동영상 재개
        }

        videoView.setOnClickListener {
            //비디오가 재생중이면
            if(videoView.isPlaying == true){
                videoView.pause()
                binding.pause.visibility = View.VISIBLE
                binding.goBack.visibility = View.VISIBLE
                binding.videoDate.visibility = View.VISIBLE
                binding.contentBar.visibility = View.VISIBLE
                binding.menuItems.visibility = View.INVISIBLE
                binding.videoTitle.visibility = View.VISIBLE
                binding.videoDetail.visibility = View.VISIBLE
                binding.menuBtn.visibility = View.VISIBLE
            }
            //비디오가 멈췄다면
            else {
                videoView.start()
                binding.goBack.visibility = View.INVISIBLE
                binding.pause.visibility = View.INVISIBLE
                binding.videoDate.visibility = View.INVISIBLE
                binding.contentBar.visibility = View.INVISIBLE
                binding.menuItems.visibility = View.INVISIBLE
                binding.videoTitle.visibility = View.INVISIBLE
                binding.videoDetail.visibility = View.INVISIBLE
                binding.menuBtn.visibility = View.INVISIBLE
                binding.play.visibility = View.VISIBLE
                val handler = Handler()
                handler.postDelayed(Runnable {
                    binding.play.visibility = View.INVISIBLE
                }, 500) //딜레이 타임 조절
            }
        }

        //뒤로가기
        binding.goBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //메뉴바
        binding.menuBtn.setOnClickListener{
            val items =  binding.menuItems
            if(items.visibility == View.VISIBLE){
                items.visibility = View.GONE

            } else{
                items.visibility = View.VISIBLE
                //참여자 추가
                binding.participant.setOnClickListener {
                    //참여자 추가 페이지로 이동
                    val intent = Intent(this, ParticipantActivity::class.java)
                    startActivity(intent)
                }
                //다운로드
                binding.downloadBook.setOnClickListener {
                    //영상 다운로드 받기
                }
                //상세설정 수정
                binding.modifyBook.setOnClickListener {
                    //수정 페이지로 이동
               }
               //삭제
               binding.deleteBook.setOnClickListener {
                   //동영상 삭제
               }
            }
        }
    }
}