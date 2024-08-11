package com.example.viewmodel.products.adapter

import Category
import CategoryResponse
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themeal_app.Data.UI.HomeFragmentDirections
import com.example.themeal_app.R

class adapter(private val context: Context, val navController: NavController,) : RecyclerView.Adapter<adapter.MyViewHolder>() {

    private var data: CategoryResponse? = null

    fun setCategoryList(categoryList: CategoryResponse) {
        this.data = categoryList
        notifyDataSetChanged()
    }

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val title: TextView = row.findViewById(R.id.product_title)
        val img: ImageView = row.findViewById(R.id.product_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return MyViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return data?.categories?.size ?: 0    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = data?.categories?.get(position) ?: return

        holder.title.text = category.strCategory
        Glide.with(context)
            .load(category.strCategoryThumb)
            .centerCrop()
            .into(holder.img)
        holder.itemView.setOnClickListener {
            val name_category = data?.categories?.get(position)?.strCategory
            val action =
                HomeFragmentDirections.actionHomeFragmentToMealsFragment(name_category.toString())
            navController.navigate(action)

            }
        }
    }

