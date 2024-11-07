package com.example.appreceitas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchRecipeActivity : AppCompatActivity() {

    private lateinit var dbHelper: RecipeDatabaseHelper
    private lateinit var adapter: RecipeAdapter
    private lateinit var allRecipes: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_recipe)

        dbHelper = RecipeDatabaseHelper(this)
        allRecipes = dbHelper.getRecipes()

        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val searchRecyclerView = findViewById<RecyclerView>(R.id.searchRecyclerView)
        searchRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RecipeAdapter(allRecipes) { selectedRecipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java).apply {
                putExtra("RECIPE_NAME", selectedRecipe.name)
                putStringArrayListExtra("INGREDIENTS", ArrayList(selectedRecipe.ingredients))
                putStringArrayListExtra("PREPARATION_STEPS", ArrayList(selectedRecipe.preparationSteps))
                putExtra("PREP_TIME", selectedRecipe.prepTime)
            }
            startActivity(intent)
        }
        searchRecyclerView.adapter = adapter

        // Adiciona o TextWatcher para atualizar a lista com base no termo de busca
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterRecipes(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterRecipes(query: String) {
        val filteredRecipes = allRecipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true)
        }
        adapter = RecipeAdapter(filteredRecipes) { selectedRecipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java).apply {
                putExtra("RECIPE_NAME", selectedRecipe.name)
                putStringArrayListExtra("INGREDIENTS", ArrayList(selectedRecipe.ingredients))
                putStringArrayListExtra("PREPARATION_STEPS", ArrayList(selectedRecipe.preparationSteps))
                putExtra("PREP_TIME", selectedRecipe.prepTime)
            }
            startActivity(intent)
        }
        findViewById<RecyclerView>(R.id.searchRecyclerView).adapter = adapter
    }
}
