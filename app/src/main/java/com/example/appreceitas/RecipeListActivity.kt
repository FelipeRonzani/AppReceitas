package com.example.appreceitas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreceitas.adapters.RecipeAdapter
import com.example.appreceitas.data.DataStore
import com.example.appreceitas.databinding.ActivityRecipeListBinding

class RecipeListActivity : AppCompatActivity() {

    private lateinit var dataStore: DataStore
    private lateinit var binding: ActivityRecipeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar o View Binding
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStore = DataStore(this)

        // Configurar o RecyclerView
        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(this)

        // Obter receitas do DataStore
        val recipes = dataStore.getRecipes()

        val adapter = RecipeAdapter(recipes) { recipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("RECIPE_ID", recipe.id)
            startActivity(intent)
        }

        binding.recipeRecyclerView.adapter = adapter
    }
}
