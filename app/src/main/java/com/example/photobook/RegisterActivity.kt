package com.example.photobook

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.photobook.databinding.ActivityLoginBinding
import com.example.photobook.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RegisterActivity : AppCompatActivity() {
    lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(registerBinding.root)

        //회원가입 완료 버튼을 눌렀을때
        registerBinding.registerBtn.setOnClickListener{
            val id = registerBinding.ID.getText().toString()
            val pass = registerBinding.Password.getText().toString()
            val repass = registerBinding.repass.getText().toString()
            val nickname = registerBinding.nickname.getText().toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(RetrofitInterface::class.java)
            val call = api.executeRegister(id, pass, nickname)

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
                    call.clone().enqueue(object : Callback<String> {
                        override fun onFailure(call: Call<String>, t: Throwable) {
//                            Log.d("mobile", t.toString())
                        }

                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            val str = response.body().toString().split(",")
                            val success = str.get(0).split(":").get(1)
                            val message = str.get(1).split(":").get(1).split("\"").get(1)

                            if(success.toBoolean() == true){

                                val builder1 = AlertDialog.Builder(this@RegisterActivity)
                                builder1.setTitle("Register Success")
                                builder1.setMessage(id + "님 회원가입되었습니다.")
                                builder1.show()

                                val intent =Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)
//                               intent.putExtra()
                                finish()
                            }
                            else{
                                Toast.makeText(this@RegisterActivity,message, Toast.LENGTH_LONG).show()
                            }
                        }

                    })
                }
                else{
                    //비밀번호가 일치하지 않는다면
                    registerBinding.passVisible.visibility = View.VISIBLE
                }

            }
        }

    }
}