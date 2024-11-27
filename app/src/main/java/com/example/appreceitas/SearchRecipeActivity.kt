package com.example.appreceitas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreceitas.adapters.RecipeAdapter
import com.example.appreceitas.data.DataStore
import com.example.appreceitas.databinding.ActivitySearchRecipeBinding

class SearchRecipeActivity : AppCompatActivity() {

    private lateinit var dataStore: DataStore
    private lateinit var adapter: RecipeAdapter
    private lateinit var binding: ActivitySearchRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar View Binding
        binding = ActivitySearchRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStore = DataStore(this)

        // Configurar RecyclerView
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecipeAdapter(emptyList()) { /* Implementar clique */ }
        binding.searchRecyclerView.adapter = adapter

        // Configurar TextWatcher para pesquisa
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredRecipes = dataStore.getRecipes().filter {
                    it.name.contains(s.toString(), ignoreCase = true)
                }
                adapter = RecipeAdapter(filteredRecipes) { recipe ->
                    val intent = Intent(this, RecipeDetailActivity::class.java)
                    intent.putExtra("RECIPE_ID", recipe.id)
                    startActivity(intent)
                }
                binding.searchRecyclerView.adapter = adapter
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }
}
