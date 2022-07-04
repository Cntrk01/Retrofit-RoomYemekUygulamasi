package com.example.foodrecipeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.entities.MealItems
import com.example.foodrecipeapp.entities.Recipies
import kotlinx.android.synthetic.main.item_rv_sub_category.view.*

class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {
    var listener : SubCategoryAdapter.OnItemClickListener?=null
    var ctx : Context?=null
    var arrSubCategory=ArrayList<MealItems>()
    class RecipeViewHolder(view: View):RecyclerView.ViewHolder(view){

    }
    fun setData(arrData:List<MealItems>){
        arrSubCategory=arrData as ArrayList<MealItems>
    }
    fun setClickListener(listener1: SubCategoryAdapter.OnItemClickListener){
        listener=listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx=parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sub_category,parent,false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.tv_dish_name.text=arrSubCategory.get(position).strMeal
        Glide.with(ctx!!).load(arrSubCategory.get(position).strMealThumb).into(holder.itemView.img_dish)
        holder.itemView.rootView.setOnClickListener {
            listener!!.OnClicked(arrSubCategory.get(position).idMeal)
        }
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }
    interface OnItemClickListener{
        fun OnClicked(id:String)
    }
}