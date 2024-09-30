package com.example.flask

import android.service.autofill.UserData
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


data class UserData(
    val login: String,
    @SerializedName("password")
    val pass: String,
    @SerializedName("image_url")
    val imageURL: String
)

interface UserService {
    @POST("/user/create/mob")
    suspend fun createUser(@Body userData: com.example.flask.UserData)

    @GET("/user/all")
    suspend fun getUsers() : List<UserData>
}

