package com.example.appreceitas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appreceitas.R

class SearchRecipeActivity : AppCompatActivity() {

    private lateinit var adapter: RecipeAdapter
    private var recipes: List<Recipe> = listOf(
        Recipe("Bolo de Cenoura", listOf("Cenoura", "Farinha", "Açúcar"), listOf("Misture", "Asse"), "40 min"),
        Recipe("Torta de Maçã", listOf("Maçã", "Açúcar", "Farinha"), listOf("Prepare a massa", "Coloque a maçã", "Asse"), "1 hora")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_recipe)

        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val searchRecyclerView = findViewById<RecyclerView>(R.id.searchRecyclerView)
        searchRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RecipeAdapter(recipes)
        searchRecyclerView.adapter = adapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchQuery = s.toString()
                filterRecipes(searchQuery)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun filterRecipes(query: String) {
        recipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true)
        }
    }
}
