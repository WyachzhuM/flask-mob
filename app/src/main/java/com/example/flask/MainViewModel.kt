package com.example.flask

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flask.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    fun createUser(binding: ActivityMainBinding) = viewModelScope.launch(dispatcher){
        try {
            repository.createUser(binding.loginEditText.text.toString(), binding.passwordEditText.text.toString())

            binding.loginEditText.text.clear()
            binding.passwordEditText.text.clear()
        } catch (e: Exception) {
            Toast.makeText(
                binding.root.context, "login must be unique",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}