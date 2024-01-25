package com.example.hyuntalk

data class User(
    var name: String,
    var email: String,
    var uId: String
){
    constructor(): this("","","")
}
