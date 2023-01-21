package com.example.photobook

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.photobook.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        //로그인 버튼 눌렀을때
        loginBinding.LoginBtn!!.setOnClickListener{
            //id 값
            val id = loginBinding.id!!.text.toString()
            //password 값
            val password = loginBinding.password!!.text.toString()

            //DB에 정보 넣기(백)

            //정보가 입력되지 않았을때
            if(id =="" || password=="") Toast.makeText(
                this@LoginActivity,
                "회원정보를 전부 입력해주세요.",
                Toast.LENGTH_SHORT
            ).show()
            //모든 정보가 입력되었을때
            else{
                //id값이 DB에 있는지 검사해야함(백)
                //만약 id값이 존재하지 않는다면(백)
                //Toast.makeText(this@LoginActivity, "회원정보가 존재하지 않습니다.", Toast.LENGTH_SHORT)
                //                    .show()


                //만약 id값이 db에 있다면 -> 로그인 성공 -> 홈화면으로 이동
                Toast.makeText(this@LoginActivity, "로그인 되었습니다.", Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }

        //비밀번호 찾기
        loginBinding.changePass.setOnClickListener {
            val loginIntent = Intent(this@LoginActivity, passwordActivity::class.java)
            startActivity(loginIntent)
        }


        //회원가입하기
        loginBinding.registerBtn.setOnClickListener {
            val loginIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(loginIntent)
        }

    }
}