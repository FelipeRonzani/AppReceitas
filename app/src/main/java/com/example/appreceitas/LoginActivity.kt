package com.example.appreceitas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateLogin(email, password)) {
                // Navegar para a tela de lista de receitas
                val intent = Intent(this, RecipeListActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateLogin(email: String, password: String): Boolean {
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val storedEmail = sharedPref.getString("email", "user@example.com")
        val storedPassword = sharedPref.getString("password", "password123")
        
        // Verifica se as credenciais inseridas são válidas
        return email == storedEmail && password == storedPassword
    }
}
