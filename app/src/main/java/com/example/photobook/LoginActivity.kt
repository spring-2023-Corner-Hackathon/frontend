package com.example.photobook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.photobook.databinding.ActivityLoginBinding
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding : ActivityLoginBinding
    lateinit var id: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        id = loginBinding.id.getText().toString()
        password = loginBinding.password.getText().toString()

//        val client = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .connectTimeout(100, TimeUnit.SECONDS)
//            .readTimeout(100, TimeUnit.SECONDS)
//            .writeTimeout(100, TimeUnit.SECONDS)
//            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RetrofitInterface::class.java)
        val call = api.executeLogin(id, password)

        //로그인 버튼 눌렀을때
        loginBinding.LoginBtn.setOnClickListener{

           call.clone().enqueue(object :Callback<LoginResult>{
               override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                   Log.d("mobile", t.toString())
               }

               override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                   Log.d("mobile", response.code().toString())

                   when(response.code()){
                        200 -> {
//                            val builder1 = AlertDialog.Builder(this@LoginActivity)
//                            builder1.setTitle("Login Success")
//                            builder1.setMessage(id + "님 환영합니다.")
//                            builder1.show()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                        405 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        500 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                    }
               }

           })

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
