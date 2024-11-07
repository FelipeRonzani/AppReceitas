package com.example.appreceitas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val recipeName = intent.getStringExtra("RECIPE_NAME")
        val ingredients = intent.getStringArrayListExtra("INGREDIENTS")
        val preparationSteps = intent.getStringArrayListExtra("PREPARATION_STEPS")
        val prepTime = intent.getStringExtra("PREP_TIME")

        findViewById<TextView>(R.id.recipeNameTextView).text = recipeName
        findViewById<TextView>(R.id.ingredientsTextView).text = "Ingredientes: ${ingredients?.joinToString(", ")}"
        findViewById<TextView>(R.id.recipeStepsTextView).text = "Modo de Preparo:\n${preparationSteps?.joinToString("\n")}"
    }
}
