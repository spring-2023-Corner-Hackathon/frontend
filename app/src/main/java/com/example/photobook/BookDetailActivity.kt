package com.example.photobook

import android.R
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.photobook.databinding.ActivityBookDetailBinding
import retrofit2.http.Url


class BookDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityBookDetailBinding
    //url경로
//    private val VIDEO_URL = "https://yongsuchul.s3.us-west-1.amazonaws.com/nickname/video/testVideo1.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.videoTitle.text = intent.getStringExtra("title")

        val videoView = binding.videoView
//        val uri: Uri = Uri.parse(VIDEO_URL)
        val VideoFile: String = Environment.getExternalStorageDirectory().absolutePath+ "/Download/testVideo1.mp4"

        videoView.setVideoPath(VideoFile) // 없으면 에러
        videoView.requestFocus()    // 준비하는 과정을 미리함

        videoView.setOnPreparedListener {
            videoView.start() // 동영상 재개
            Toast.makeText(this@BookDetailActivity, "영상시작", Toast.LENGTH_SHORT).show()
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
            val intent = Intent(this@BookDetailActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
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
//                binding.downloadBook.setOnClickListener {
//                    //영상 다운로드 받기
//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_URL))
//                    startActivity(intent)
//                }
                //상세설정 수정
                binding.modifyBook.setOnClickListener {
                    //수정 페이지로 이동
                    val intent = Intent(this@BookDetailActivity, ModifyBookActivity::class.java)
                    startActivity(intent)
                }
                //삭제
                binding.deleteBook.setOnClickListener {
                    //동영상 삭제
                }
            }
        }
    }
}