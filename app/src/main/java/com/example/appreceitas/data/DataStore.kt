package com.example.appreceitas.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class DataStore(context: Context) {
    private val file = File(context.filesDir, "data.json")
    private val gson = Gson()

    init {
        if (!file.exists()) {
            file.writeText(context.assets.open("data.json").bufferedReader().use { it.readText() })
        }
    }

    fun getUsers(): List<User> {
        val json = file.readText()
        val type = object : TypeToken<Map<String, List<User>>>() {}.type
        val data: Map<String, List<User>> = gson.fromJson(json, type)
        return data["users"] ?: emptyList()
    }

    fun getRecipes(): List<Recipe> {
        val json = file.readText()
        val type = object : TypeToken<Map<String, List<Recipe>>>() {}.type
        val data: Map<String, List<Recipe>> = gson.fromJson(json, type)
        return data["recipes"] ?: emptyList()
    }

    fun addUser(user: User) {
        val users = getUsers().toMutableList()
        users.add(user)
        val json = gson.toJson(mapOf("users" to users, "recipes" to getRecipes()))
        file.writeText(json)
    }
}
