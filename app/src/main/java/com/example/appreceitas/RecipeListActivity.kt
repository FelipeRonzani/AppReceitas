package com.example.appreceitas

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.appreceitas.R
import com.example.appreceitas.data.RecipeRepository

class RecipeListActivity : AppCompatActivity() {

    private lateinit var recipeAdapter: RecipeAdapter
    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory(RecipeRepository(RecipeDatabase.getDatabase(this).recipeDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        recipeRecyclerView.layoutManager = LinearLayoutManager(this)
        
        recipeAdapter = RecipeAdapter(emptyList()) { recipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java).apply {
                putExtra("RECIPE_ID", recipe.id)
            }
            startActivity(intent)
        }
        
        recipeRecyclerView.adapter = recipeAdapter

        // Obter receitas do ViewModel e atualizar o adapter
        recipeViewModel.getAllRecipes { recipes ->
            recipeAdapter.updateData(recipes)
        }
    }
}
