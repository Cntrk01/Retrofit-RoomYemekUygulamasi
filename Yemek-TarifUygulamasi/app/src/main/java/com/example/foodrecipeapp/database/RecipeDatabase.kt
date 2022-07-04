package com.example.foodrecipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipeapp.dao.RecipiesDao
import com.example.foodrecipeapp.entities.*
import com.example.foodrecipeapp.entities.conventer.CategoryListConventer
import com.example.foodrecipeapp.entities.conventer.MealListConventer

@Database(entities = [Recipies::class,CategoryItems::class,Category::class,Meal::class,MealItems::class], version = 2, exportSchema = true)
@TypeConverters(CategoryListConventer::class,MealListConventer::class)
abstract class RecipeDatabase :RoomDatabase(){
    abstract fun recipeDao():RecipiesDao
    companion object{
        var recipeDatabase:RecipeDatabase?=null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase{
            if (recipeDatabase ==null){
                recipeDatabase=Room.databaseBuilder(context,RecipeDatabase::class.java,"recipe.db").build()
            }
            return recipeDatabase!!
        }
    }
}