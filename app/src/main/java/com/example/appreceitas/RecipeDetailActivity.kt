package com.example.appreceitas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appreceitas.data.DataStore
import com.example.appreceitas.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var dataStore: DataStore
    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar View Binding
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStore = DataStore(this)

        // Recuperar o ID da receita do Intent
        val recipeId = intent.getIntExtra("RECIPE_ID", -1)
        val recipe = dataStore.getRecipes().find { it.id == recipeId }

        // Exibir os detalhes da receita, se encontrados
        recipe?.let {
            binding.recipeNameTextView.text = it.name
            binding.recipeIngredientsTextView.text = "Ingredientes: ${it.ingredients}"
            binding.recipePreparationTextView.text = "Modo de Preparo: ${it.preparation}"

            // Carregar a imagem do drawable
            val imageResId = resources.getIdentifier(it.image, "drawable", packageName)
            binding.recipeImageView.setImageResource(imageResId)
        }
    }
}
