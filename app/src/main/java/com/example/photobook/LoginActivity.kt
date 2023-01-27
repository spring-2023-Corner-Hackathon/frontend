package com.example.photobook

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.photobook.databinding.ActivityLoginBinding
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)



        //로그인 버튼 눌렀을때
        loginBinding.LoginBtn.setOnClickListener{
            val id = loginBinding.id.getText().toString()
            val password = loginBinding.password.getText().toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(RetrofitInterface::class.java)
            val call = api.executeLogin(id, password)

            Log.d("mobile", id + password)

            if(id.length == 0 || password.length == 0)
                Toast.makeText(this@LoginActivity, "아이디나 비밀번호를 입력하시오", Toast.LENGTH_LONG).show()

            else{
                call.clone().enqueue(object :Callback<LoginResult>{
                    override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                        Log.d("mobile", t.toString())
                    }

                    override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                        if(response.isSuccessful){
                            Log.d("mobile", response.toString())

                                val builder1 = AlertDialog.Builder(this@LoginActivity)
                                builder1.setTitle("Login Success")
                                builder1.setMessage(id + "님 환영합니다.")
                                builder1.show()

                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
//                                intent.putExtra()
                                finish()
                         }
                    }

                })
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
