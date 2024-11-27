package com.example.appreceitas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreceitas.adapters.RecipeAdapter
import com.example.appreceitas.data.DataStore
import com.example.appreceitas.databinding.ActivityRecipeListBinding

class RecipeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var dataStore: DataStore
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar View Binding
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStore = DataStore(this)

        // Configurar RecyclerView
        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(this)
        val recipes = dataStore.getRecipes()

        adapter = RecipeAdapter(recipes) { recipe ->
            // Abrir a tela de detalhes da receita
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("RECIPE_ID", recipe.id)
            startActivity(intent)
        }
        binding.recipeRecyclerView.adapter = adapter

        // Configurar botão de pesquisa
        binding.searchButton.setOnClickListener {
            // Iniciar a SearchRecipeActivity
            val intent = Intent(this, SearchRecipeActivity::class.java)
            startActivity(intent)
        }
    }
}
