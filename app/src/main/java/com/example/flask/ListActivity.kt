package com.example.flask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.flask.databinding.ActivityListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = UsersListAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        GlobalScope.launch(Dispatchers.Main){
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.3.153:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(UserService::class.java)

            val usersData = withContext(Dispatchers.IO){
                return@withContext service.getUsers()
            }

            adapter.update(usersData)
        }

        binding.buttonToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }



}