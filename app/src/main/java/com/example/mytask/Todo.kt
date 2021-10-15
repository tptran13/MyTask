package com.example.mytask

//a data class who primary purpose is to hold data
//no logic needed
data class Todo(
    val title:String,
    var isChecked: Boolean= false
)