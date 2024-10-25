package com.example.appreceitas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.appreceitas.R

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val recipeName = intent.getStringExtra("RECIPE_NAME")
        val ingredients = intent.getStringArrayListExtra("INGREDIENTS")
        val preparationSteps = intent.getStringArrayListExtra("PREPARATION_STEPS")
        val prepTime = intent.getStringExtra("PREP_TIME")

        val recipeNameTextView = findViewById<TextView>(R.id.recipeNameTextView)
        val ingredientsTextView = findViewById<TextView>(R.id.ingredientsTextView)
        val recipeStepsTextView = findViewById<TextView>(R.id.recipeStepsTextView)

        recipeNameTextView.text = recipeName
        ingredientsTextView.text = "Ingredientes: ${ingredients?.joinToString(", ")}"
        recipeStepsTextView.text = "Modo de Preparo:\n${preparationSteps?.joinToString("\n")}"
    }
}
