package com.example.themeal_app.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.DatabaseModel.model.Meal
import com.example.themeal_app.R

class favAdapter(
    private var recipes: List<Meal>,
    private val onDeleteClick: (Meal) -> Unit
) : RecyclerView.Adapter<favAdapter.RecipeViewHolder>() {

    // ViewHolder يمثل عنصر العرض في القائمة
    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeTitle: TextView = view.findViewById(R.id.recipe_title)
        val recipeDescription: TextView = view.findViewById(R.id.recipe_description)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
    }

    // إنشاء ViewHolder جديد
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return RecipeViewHolder(view)
    }

    // ربط البيانات بعنصر العرض
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeTitle.text = recipe.strMeal
        holder.recipeDescription.text = recipe.strMealThumb  // هنا استخدمنا strMealThumb كمثال، يمكنك تعديله ليحتوي على الوصف الفعلي إذا كان موجودًا.

        // عند الضغط على زر "Delete"
        holder.deleteButton.setOnClickListener {
            onDeleteClick(recipe)  // استدعاء الدالة لحذف الوصفة
        }
    }

    // عدد العناصر في القائمة
    override fun getItemCount(): Int {
        return recipes.size
    }

    // تحديث البيانات في الـ RecyclerView
    fun updateRecipes(newRecipes: List<Meal>) {
        recipes = newRecipes
        notifyDataSetChanged()  // تحديث العرض بعد تغيير البيانات
    }
}
