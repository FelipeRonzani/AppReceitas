package com.appreceitas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_recipe)

        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val searchRecyclerView = findViewById<RecyclerView>(R.id.searchRecyclerView)
        searchRecyclerView.layoutManager = LinearLayoutManager(this)

        val recipes = listOf(
            Recipe("Bolo de Cenoura", listOf("Cenoura", "Farinha", "Açúcar"), listOf("Misture", "Asse"), "40 min"),
            Recipe("Torta de Maçã", listOf("Maçã", "Açúcar", "Farinha"), listOf("Prepare a massa", "Coloque a maçã", "Asse"), "1 hora")
        )

        val adapter = RecipeAdapter(recipes) { selectedRecipe ->
            // Lógica ao clicar em um item
        }
        searchRecyclerView.adapter = adapter

        // Adicionar lógica de pesquisa com EditText
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Não faz nada aqui
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchQuery = s.toString()
                val filteredRecipes = recipes.filter { recipe ->
                    recipe.name.contains(searchQuery, ignoreCase = true)
                }
                adapter.updateList(filteredRecipes)
            }

            override fun afterTextChanged(s: Editable?) {
                // Não faz nada aqui
            }
        })
    }
}
