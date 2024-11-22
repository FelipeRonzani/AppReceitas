package com.example.appreceitas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appreceitas.data.DataStore
import com.example.appreceitas.data.User
import com.example.appreceitas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var dataStore: DataStore
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar o View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStore = DataStore(this)

        // Configurar ações dos botões
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            val user = dataStore.getUsers().find { it.email == email && it.password == password }
            if (user != null) {
                startActivity(Intent(this, RecipeListActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            dataStore.addUser(User(email, password))
            Toast.makeText(this, "Usuário registrado com sucesso", Toast.LENGTH_SHORT).show()
        }
    }
}
