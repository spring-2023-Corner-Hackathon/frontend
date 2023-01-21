package com.example.photobook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.photobook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainbinding.root)

        //spinner 내용 넣기
        val data = resources.getStringArray(R.array.spinner_array)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        mainbinding.sort.adapter = adapter

        mainbinding.goLogin.setOnClickListener{
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}