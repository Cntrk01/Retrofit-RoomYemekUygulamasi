package com.example.foodrecipeapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.foodrecipeapp.entities.conventer.MealListConventer
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Meal")
class Meal(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "meals")
    @Expose
    @SerializedName("meals")
    @TypeConverters(MealListConventer::class)
    var mealitem:List<MealItems>?=null


)
