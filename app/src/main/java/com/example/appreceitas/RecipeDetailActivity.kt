package com.example.appreceitas

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appreceitas.data.Recipe
import com.example.appreceitas.data.RecipeRepository
import com.example.appreceitas.RecipeDatabase
import com.appreceitas.R

class RecipeDetailActivity : AppCompatActivity() {

    // Delegação para o ViewModel com o factory para injeção de dependência
    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory(RecipeRepository(RecipeDatabase.getDatabase(this).recipeDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        // Obter o ID da receita a partir do Intent
        val recipeId = intent.getIntExtra("RECIPE_ID", -1)
        if (recipeId != -1) {
            // Chamar o método do ViewModel para buscar a receita pelo ID
            recipeViewModel.getRecipeById(recipeId) { recipe ->
                displayRecipeDetails(recipe)
            }
        }
    }

    private fun displayRecipeDetails(recipe: Recipe) {
        // Definir os valores das views com os dados da receita
        findViewById<TextView>(R.id.recipeNameTextView).text = recipe.name
        findViewById<TextView>(R.id.ingredientsTextView).text = "Ingredientes: ${recipe.ingredients}"
        findViewById<TextView>(R.id.recipeStepsTextView).text = "Modo de Preparo:\n${recipe.preparationSteps}"
    }
}
