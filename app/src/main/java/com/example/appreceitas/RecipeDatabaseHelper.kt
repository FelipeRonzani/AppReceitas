package com.example.appreceitas
package com.seuprojeto

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class RecipeDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "recipes.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_RECIPES = "recipes"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_INGREDIENTS = "ingredients"
        const val COLUMN_PREPARATION = "preparation"
        const val COLUMN_PREP_TIME = "prep_time"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = """
            CREATE TABLE $TABLE_RECIPES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_INGREDIENTS TEXT,
                $COLUMN_PREPARATION TEXT,
                $COLUMN_PREP_TIME TEXT
            )
        """
        db?.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_RECIPES")
        onCreate(db)
    }

    // Função para inserir uma receita no banco de dados
    fun insertRecipe(name: String, ingredients: String, preparation: String, prepTime: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_INGREDIENTS, ingredients)
            put(COLUMN_PREPARATION, preparation)
            put(COLUMN_PREP_TIME, prepTime)
        }
        db.insert(TABLE_RECIPES, null, values)
        db.close()
    }

    // Função para verificar se a tabela de receitas está vazia
    private fun isTableEmpty(): Boolean {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_RECIPES", null)
        var count = 0
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return count == 0
    }

    // Função para inserir receitas iniciais
    fun insertInitialRecipes() {
        if (isTableEmpty()) {
            val initialRecipes = listOf(
                Recipe("Bolo de Cenoura", "Cenoura,Farinha,Ovos,Açúcar,Óleo", "Misture,Asse", "45 min"),
                Recipe("Torta de Maçã", "Maçã,Farinha,Ovos,Açúcar,Canela", "Misture,Coloque a maçã,Asse", "60 min"),
                Recipe("Panqueca", "Farinha,Ovos,Leite,Açúcar,Óleo", "Misture,Frigideira", "15 min"),
                Recipe("Brigadeiro", "Leite Condensado,Chocolate Granulado", "Misture,Cozinhe até soltar do fundo", "20 min"),
                Recipe("Mousse de Maracujá", "Leite Condensado,Creme de Leite,Maracujá", "Misture,Refrigere", "3 horas"),
                Recipe("Arroz de Forno", "Arroz,Queijo,Presunto,Ervilha,Milho", "Misture,Asse", "30 min"),
                Recipe("Salada de Frutas", "Maçã,Banana,Uva,Morango,Laranja", "Pique,Misture", "10 min"),
                Recipe("Pizza de Frigideira", "Farinha,Ovo,Água,Recheio a gosto", "Misture,Frigideira", "20 min"),
                Recipe("Macarrão Carbonara", "Macarrão,Bacon,Ovos,Queijo", "Cozinhe,Adicione ovos e bacon", "20 min"),
                Recipe("Pudim", "Leite Condensado,Leite,Ovos,Açúcar", "Misture,Asse em banho-maria", "1 hora")
            )

            initialRecipes.forEach { recipe ->
                insertRecipe(
                    recipe.name,
                    recipe.ingredients.joinToString(","),
                    recipe.preparationSteps.joinToString(","),
                    recipe.prepTime
                )
            }
        }
    }
}

