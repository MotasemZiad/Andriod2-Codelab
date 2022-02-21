package com.motasem.ziad.assignmentlab6


import com.motasem.ziad.assignmentlab6.model.ToDo
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {

    @GET("todos")
    fun getToDos(): Call<ToDo>
}