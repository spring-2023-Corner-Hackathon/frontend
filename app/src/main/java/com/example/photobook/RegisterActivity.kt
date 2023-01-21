package com.example.photobook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.photobook.databinding.ActivityLoginBinding
import com.example.photobook.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(registerBinding.root)

        //회원가입 완료 버튼을 눌렀을때
        registerBinding.registerBtn.setOnClickListener{
            val id = registerBinding.ID.toString()
            val pass = registerBinding.Password.getText().toString()
            val repass = registerBinding.repass.getText().toString()
            val nickname = registerBinding.nickname.toString()

            //모든정보가 입력되지 않았을때
            if (id == "" || pass == "" || repass == "" || nickname == "")

                Toast.makeText(
                    this@RegisterActivity,
                    "회원정보를 전부 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()

            else{

                if (pass == repass){
                    registerBinding.passVisible.visibility = View.INVISIBLE

                    //서버로 JSON 형식으로 보내기


                    Toast.makeText(
                        this@RegisterActivity,
                        "가입되었습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                }
                else{
                    //비밀번호가 일치하지 않는다면
                    registerBinding.passVisible.visibility = View.VISIBLE
                }

            }
        }

        //아이디 중복확인 버튼을 눌렀을때
        registerBinding.overlapId.setOnClickListener{

            //만약 아이디가 중복된다면(백)
            //'이미 가입된 회원입니다.' 표시
            registerBinding.idVisible.visibility = View.VISIBLE

            //아이디가 중복되지 않는다면(백)
            //registerBinding.idVisible.visibility = View.INVISIBLE
        }

        //닉네임 중복확인 버튼을 눌렀을때
        registerBinding.overlapNickname.setOnClickListener{

            //만약 닉네임이 중복된다면(백)
            //'중복된 닉네임입니다.' 표시
            registerBinding.nicknameVisible.visibility = View.VISIBLE

            //닉네임이 중복되지 않는다면(백)
            //registerBinding.nicknameVisible.visibility = View.INVISIBLE
        }
    }
}