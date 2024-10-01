package com.appreceitas

data class Recipe(
    val name: String,
    val ingredients: List<String>,
    val preparationSteps: List<String>,
    val prepTime: String
)
