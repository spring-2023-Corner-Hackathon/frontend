package com.example.photobook

import java.util.*
import kotlin.collections.ArrayList


data class Users(
    var id:String,
)

data class Music(
    var id:String
)
data class Photobooks(
    var title:String,
    var description:String,
    var editors:ArrayList<Users>,
    var video:String,
    var music:ArrayList<Music>,
    var share:String,
    var category:String,
    var start: Date,
    var end:Date,


)