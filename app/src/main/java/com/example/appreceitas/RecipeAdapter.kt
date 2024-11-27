package com.example.appreceitas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appreceitas.R
import com.example.appreceitas.data.Recipe

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipeImageView)
        val recipeName: TextView = itemView.findViewById(R.id.recipeNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeName.text = recipe.name
        
        // Carregar a imagem diretamente do drawable
        val context = holder.itemView.context
        val imageResId = context.resources.getIdentifier(recipe.image, "drawable", context.packageName)
        holder.recipeImage.setImageResource(imageResId)

        // Configurar o clique no item
        holder.itemView.setOnClickListener { onRecipeClick(recipe) }
    }

    override fun getItemCount(): Int = recipes.size
}
