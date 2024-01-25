package com.example.hyuntalk

data class Message(
    var message: String?,
    var sendId: String?
){
    constructor():this("","")
}
