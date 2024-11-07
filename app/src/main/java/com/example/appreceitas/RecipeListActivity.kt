package com.example.appreceitas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeListActivity : AppCompatActivity() {

    private lateinit var dbHelper: RecipeDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        dbHelper = RecipeDatabaseHelper(this)

        // Insere as receitas iniciais, se necessário
        dbHelper.insertInitialRecipes()

        // Configura o RecyclerView para exibir as receitas
        val recipeRecyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)

        val recipes = dbHelper.getRecipes()
        val adapter = RecipeAdapter(recipes) { selectedRecipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java).apply {
                putExtra("RECIPE_NAME", selectedRecipe.name)
                putStringArrayListExtra("INGREDIENTS", ArrayList(selectedRecipe.ingredients))
                putStringArrayListExtra("PREPARATION_STEPS", ArrayList(selectedRecipe.preparationSteps))
                putExtra("PREP_TIME", selectedRecipe.prepTime)
            }
            startActivity(intent)
        }
        recipeRecyclerView.adapter = adapter

        // Configuração do botão de pesquisa
        val searchButton = findViewById<Button>(R.id.searchButton)
        searchButton.setOnClickListener {
            val intent = Intent(this, SearchRecipeActivity::class.java)
            startActivity(intent)
        }
    }
}
