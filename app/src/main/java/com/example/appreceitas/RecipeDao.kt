package com.example.appreceitas

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appreceitas.data.Recipe

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE name LIKE :searchQuery")
    suspend fun searchRecipes(searchQuery: String): List<Recipe>
}
