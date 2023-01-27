package com.example.photobook

data class LoginResult(
    var user_id : String,
    var password: String
)

data class RegisterResult(
    var user_id: String,
    var password: String,
    var nickname: String
)
