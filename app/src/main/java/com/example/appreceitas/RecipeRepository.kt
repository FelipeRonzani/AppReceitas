package com.example.appreceitas.data

import com.example.appreceitas.RecipeDao

class RecipeRepository(private val recipeDao: RecipeDao) {

    suspend fun insertRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun getAllRecipes(): List<Recipe> {
        return recipeDao.getAllRecipes()
    }

    suspend fun searchRecipes(query: String): List<Recipe> {
        return recipeDao.searchRecipes("%$query%")
    }
}
