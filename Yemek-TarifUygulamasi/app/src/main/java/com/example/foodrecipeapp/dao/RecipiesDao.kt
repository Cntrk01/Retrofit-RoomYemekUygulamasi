package com.example.foodrecipeapp.dao

import androidx.room.*
import com.example.foodrecipeapp.entities.Category
import com.example.foodrecipeapp.entities.CategoryItems
import com.example.foodrecipeapp.entities.MealItems
import com.example.foodrecipeapp.entities.Recipies

@Dao
interface RecipiesDao {

    @Query("SELECT*FROM categoryitems ORDER BY id DESC")
    suspend fun getCategory():List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryItems)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(category: MealItems)

    @Query("DELETE FROM categoryitems")
    suspend fun cleardb()

    @Query("SELECT*FROM MealItems WHERE categoryName=:categoryName ORDER BY id DESC")
    suspend fun getSpecificMealList(categoryName:String ):List<MealItems>
}