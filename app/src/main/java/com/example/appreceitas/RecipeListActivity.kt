package com.example.appreceitas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appreceitas.R

class RecipeListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        val recipeRecyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)

        val recipes = listOf(
            Recipe("Bolo de Cenoura", listOf("Cenoura", "Farinha", "Açúcar"), listOf("Misture", "Asse"), "40 min"),
            Recipe("Torta de Maçã", listOf("Maçã", "Açúcar", "Farinha"), listOf("Prepare a massa", "Coloque a maçã", "Asse"), "1 hora")
        )

        val adapter = RecipeAdapter(recipes) /*{ selectedRecipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("RECIPE_NAME", selectedRecipe.name)
            intent.putStringArrayListExtra("INGREDIENTS", ArrayList(selectedRecipe.ingredients))
            intent.putStringArrayListExtra("PREPARATION_STEPS", ArrayList(selectedRecipe.preparationSteps))
            intent.putExtra("PREP_TIME", selectedRecipe.prepTime)
            startActivity(intent)
        }*/

        recipeRecyclerView.adapter = adapter
    }
}
