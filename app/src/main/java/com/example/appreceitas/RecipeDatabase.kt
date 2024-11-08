package com.example.appreceitas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appreceitas.data.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                )
                .addCallback(RecipeDatabaseCallback(context))
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class RecipeDatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Inserir receitas padrão
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database.recipeDao())
                    }
                }
            }

            suspend fun populateDatabase(recipeDao: RecipeDao) {
                val initialRecipes = listOf(
                    Recipe(
                        name = "Bolo de Cenoura",
                        description = "Um bolo clássico de cenoura com cobertura de chocolate.",
                        ingredients = "Cenoura, Açúcar, Farinha, Ovos, Óleo, Chocolate",
                        preparationSteps = "1. Misture os ingredientes. 2. Asse a massa.",
                        imageUrl = null
                    ),
                    Recipe(
                        name = "Torta de Maçã",
                        description = "Uma torta deliciosa com recheio de maçãs frescas.",
                        ingredients = "Maçã, Açúcar, Farinha, Manteiga, Canela",
                        preparationSteps = "1. Prepare a massa. 2. Adicione as maçãs. 3. Asse a torta.",
                        imageUrl = null
                    ),
                    Recipe(
                        name = "Panqueca",
                        description = "Panquecas fofinhas perfeitas para o café da manhã.",
                        ingredients = "Farinha, Leite, Ovos, Açúcar, Óleo",
                        preparationSteps = "1. Misture os ingredientes. 2. Cozinhe em fogo médio.",
                        imageUrl = null
                    )
                )

                // Inserir as receitas no banco de dados
                initialRecipes.forEach { recipe ->
                    recipeDao.insertRecipe(recipe)
                }
            }
        }
    }
}
