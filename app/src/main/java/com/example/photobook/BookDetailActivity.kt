package com.example.photobook

import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.photobook.databinding.ActivityBookDetailBinding


class BookDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val getUrl = intent.getStringExtra("url")
//        Toast.makeText(this, getUrl, Toast.LENGTH_SHORT).show()

//        binding.webView.loadUrl(getUrl.toString())
        val Video = binding.videoBook
        Video.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+R.raw.testvideo1))
        Video.setMediaController(MediaController(this))     // 없으면 에러
//
//        Video.setOnClickListener {
//            if(Video.isPlaying == true){
//                Video.pause()
//                binding.pause.visibility = View.VISIBLE
//                binding.goBack.visibility = View.VISIBLE
//                binding.contentBar.visibility = View.VISIBLE
//                binding.menuBtn.visibility = View.VISIBLE
//            } else {
//                Video.start()
//                binding.goBack.visibility = View.INVISIBLE
//                binding.contentBar.visibility = View.INVISIBLE
//                binding.menuBtn.visibility = View.INVISIBLE
//                binding.play.visibility = View.VISIBLE
//                val handler = Handler()
//                handler.postDelayed(Runnable {
//                    binding.play.visibility = View.INVISIBLE
//                    finish()
//                }, 1000) //딜레이 타임 조절
//            }
//        }


    }
}