package com.example.flask

import android.service.autofill.UserData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


data class UserData(
    val login: String,
    val pass: String
)

interface UserService {
    @POST("/user/create/mob")
    suspend fun createUser(@Body userData: com.example.flask.UserData)

    @GET("/user/all")
    suspend fun getUsers() : List<UserData>
}

