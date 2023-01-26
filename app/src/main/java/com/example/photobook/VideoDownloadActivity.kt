package com.example.photobook

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photobook.databinding.ActivityVideoDownloadBinding

class VideoDownloadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_video_download)

        val binding = ActivityVideoDownloadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.videoDownload.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://yongsuchul.s3.us-west-1.amazonaws.com/nickname/video/testVideo1.mp4"))
            startActivity(intent)

        }
    }
}