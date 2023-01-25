package com.example.photobook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.photobook.databinding.ActivityPasswordBinding

class passwordActivity : AppCompatActivity() {
    lateinit var binding:ActivityPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changeBtn.setOnClickListener{
            val pass = binding.Password.getText().toString()
            val newPass = binding.newPass.getText().toString()
            val rePass = binding.rePass.getText().toString()

            if (pass == "" || newPass == "" || rePass == "")

                Toast.makeText(
                    this@passwordActivity,
                    "회원정보를 전부 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()

            else{
                //현재 id로 정보 가져오기
                //현재 프로필과 pass가 맞는 지 확인
                //맞지 않으면 visibility = View.VISIBLE

                if(newPass == rePass){
                    binding.newVisible.visibility = View.INVISIBLE
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                }
                else{
                    binding.newVisible.visibility = View.VISIBLE
                }
            }

        }

    }
}