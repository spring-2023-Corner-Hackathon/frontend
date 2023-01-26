package com.example.photobook

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.SeekBar
import android.widget.Toast
import com.example.photobook.databinding.ActivityChooseMusicBinding
import java.text.SimpleDateFormat

class ChooseMusic : AppCompatActivity() {
    lateinit var selectedMp3: String // 현재 선택된 mp3 파일
    var editPoint: Int = 0 // 노래당 편집점 개수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_choose_music3)

        var mPlayer = MediaPlayer.create(this, R.raw.colors)
        val binding = ActivityChooseMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val musicCategory = intent.getStringExtra("musicCategory")

        binding.selectedMusicCategory.text = musicCategory

        binding.musicRg.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId) {
                R.id.colors -> {
                    selectedMp3 = "colors"
                    editPoint = 9
                    if (mPlayer.isPlaying) {
                        mPlayer.stop()
                    }
                    mPlayer = MediaPlayer.create(this, R.raw.colors)
                    mPlayer.start()

                    // 시크바 구현
                    object : Thread() {
                        var timeFormat = SimpleDateFormat("mm:ss")
                        override fun run() {
                            super.run()
                            if (mPlayer == null)
                                return
                            binding.seekBar.max = mPlayer.duration
                            while (mPlayer.isPlaying) {
                                runOnUiThread {
                                    binding.seekBar.progress = mPlayer.currentPosition
                                    binding.textView2.text =
                                        "" + timeFormat.format(mPlayer.currentPosition)
                                }
                                SystemClock.sleep(200)
                            }
                        }
                    }.start()

                }
                R.id.lamour -> {
                    selectedMp3 = "lamour"
                    editPoint = 6
                    if (mPlayer.isPlaying) {
                        mPlayer.stop()
                    }
                    mPlayer = MediaPlayer.create(this, R.raw.lamour)
                    mPlayer.start()

                    // 시크바 구현
                    object : Thread() {
                        var timeFormat = SimpleDateFormat("mm:ss")
                        override fun run() {
                            super.run()
                            if (mPlayer == null)
                                return
                            binding.seekBar.max = mPlayer.duration
                            while (mPlayer.isPlaying) {
                                runOnUiThread {
                                    binding.seekBar.progress = mPlayer.currentPosition
                                    binding.textView2.text =
                                        "" + timeFormat.format(mPlayer.currentPosition)
                                }
                                SystemClock.sleep(200)
                            }
                        }
                    }.start()
                }
                R.id.flamingo -> {
                    selectedMp3 = "flamingo"
                    editPoint = 7
                    if (mPlayer.isPlaying) {
                        mPlayer.stop()
                    }
                    mPlayer = MediaPlayer.create(this, R.raw.flamingo)
                    mPlayer.start()

                    // 시크바 구현
                    object : Thread() {
                        var timeFormat = SimpleDateFormat("mm:ss")
                        override fun run() {
                            super.run()
                            if (mPlayer == null)
                                return
                            binding.seekBar.max = mPlayer.duration
                            while (mPlayer.isPlaying) {
                                runOnUiThread {
                                    binding.seekBar.progress = mPlayer.currentPosition
                                    binding.textView2.text =
                                        "" + timeFormat.format(mPlayer.currentPosition)
                                }
                                SystemClock.sleep(200)
                            }
                        }
                    }.start()
                }
                R.id.weekend -> {
                    selectedMp3 = "weekend"
                    editPoint = 12
                    if (mPlayer.isPlaying) {
                        mPlayer.stop()
                    }
                    mPlayer = MediaPlayer.create(this, R.raw.weekend)
                    mPlayer.start()

                    // 시크바 구현
                    object : Thread() {
                        var timeFormat = SimpleDateFormat("mm:ss")
                        override fun run() {
                            super.run()
                            if (mPlayer == null)
                                return
                            binding.seekBar.max = mPlayer.duration
                            while (mPlayer.isPlaying) {
                                runOnUiThread {
                                    binding.seekBar.progress = mPlayer.currentPosition
                                    binding.textView2.text =
                                        "" + timeFormat.format(mPlayer.currentPosition)
                                }
                                SystemClock.sleep(200)
                            }
                        }
                    }.start()
                }
            }
        }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser:Boolean) {
                if(fromUser) {
                    mPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }
        })

        binding.coverBack.setOnClickListener {
            if (mPlayer.isPlaying) {
                mPlayer.stop()
            }
            val intent = Intent(this, MusicCategory::class.java)
            startActivity(intent)
        }

        binding.coverNext.setOnClickListener {
            if(!binding.colors.isChecked&&!binding.lamour.isChecked&&
                    !binding.flamingo.isChecked&&!binding.colors.isChecked) {
                Toast.makeText(this, "카테고리를 한 가지 선택해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                if (mPlayer.isPlaying) {
                    mPlayer.stop()
                }
                val intent = Intent(this, ChooseMusic::class.java)
                intent.putExtra("musicName", selectedMp3)
                intent.putExtra("editPoint", editPoint)
                startActivity(intent)
            }
        }

    }
}