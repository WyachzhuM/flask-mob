package com.example.flask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.flask.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonToList.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.3.214:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(UserService::class.java)


            GlobalScope.launch(Dispatchers.Main) {
                try {
                    withContext(Dispatchers.IO) {
                        service.createUser(UserData(login, pass, "БАШУиеавч.png"))
                    }

                    binding.loginEditText.text.clear()
                    binding.passwordEditText.text.clear()
                } catch (e: Exception) {
                    Toast.makeText(
                        this@MainActivity, "login must be unique",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            startActivity(Intent(this, ListActivity::class.java))
        }
    }
}