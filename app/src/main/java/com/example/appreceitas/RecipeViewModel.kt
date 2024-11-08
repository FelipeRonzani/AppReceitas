package com.example.appreceitas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appreceitas.data.Recipe
import com.example.appreceitas.data.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    fun insertRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    fun getAllRecipes(onResult: (List<Recipe>) -> Unit) {
        viewModelScope.launch {
            onResult(repository.getAllRecipes())
        }
    }

    fun searchRecipes(query: String, onResult: (List<Recipe>) -> Unit) {
        viewModelScope.launch {
            onResult(repository.searchRecipes(query))
        }
    }

    fun getRecipeById(recipeId: Int, any: Any) {

    }
}
