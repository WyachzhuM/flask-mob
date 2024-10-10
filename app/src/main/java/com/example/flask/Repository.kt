package com.example.flask

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val service: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getUsers(): List<UserData>{
        return withContext(dispatcher){
            return@withContext service.getUsers()
        }
    }

    suspend fun createUser(login: String, password: String){
        withContext(dispatcher){
            service.createUser(UserData(login, password, "БАШУиеавч.png"))
        }
    }
}