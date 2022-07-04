package com.example.foodrecipeapp.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MealResponse(
    @Expose
    @SerializedName("meals")
    var mealsEntity: List<MealsEntity>
)

data class MealsEntity(
    @Expose
    @SerializedName("idMeal")
    val ıdmeal: String,
    @Expose
    @SerializedName("strMeal")
    val strmeal: String,
    @Expose
    @SerializedName("strCategory")
    val strcategory: String,
    @Expose
    @SerializedName("strArea")
    val strarea: String,
    @Expose
    @SerializedName("strInstructions")
    val strınstructions: String,
    @Expose
    @SerializedName("strMealThumb")
    val strmealthumb: String,
    @Expose
    @SerializedName("strTags")
    val strtags: String,
    @Expose
    @SerializedName("strYoutube")
    val stryoutube: String,
    @Expose
    @SerializedName("strIngredient1")
    val strıngredient1: String,
    @Expose
    @SerializedName("strIngredient2")
    val strıngredient2: String,
    @Expose
    @SerializedName("strIngredient3")
    val strıngredient3: String,
    @Expose
    @SerializedName("strIngredient4")
    val strıngredient4: String,
    @Expose
    @SerializedName("strIngredient5")
    val strıngredient5: String,
    @Expose
    @SerializedName("strIngredient6")
    val strıngredient6: String,
    @Expose
    @SerializedName("strIngredient7")
    val strıngredient7: String,
    @Expose
    @SerializedName("strIngredient8")
    val strıngredient8: String,
    @Expose
    @SerializedName("strIngredient9")
    val strıngredient9: String,
    @Expose
    @SerializedName("strIngredient10")
    val strıngredient10: String,
    @Expose
    @SerializedName("strIngredient11")
    val strıngredient11: String,
    @Expose
    @SerializedName("strIngredient12")
    val strıngredient12: String,
    @Expose
    @SerializedName("strIngredient13")
    val strıngredient13: String,
    @Expose
    @SerializedName("strIngredient14")
    val strıngredient14: String,
    @Expose
    @SerializedName("strIngredient15")
    val strıngredient15: String,
    @Expose
    @SerializedName("strIngredient16")
    val strıngredient16: String,
    @Expose
    @SerializedName("strIngredient17")
    val strıngredient17: String,
    @Expose
    @SerializedName("strIngredient18")
    val strıngredient18: String,
    @Expose
    @SerializedName("strIngredient19")
    val strıngredient19: String,
    @Expose
    @SerializedName("strIngredient20")
    val strıngredient20: String,
    @Expose
    @SerializedName("strMeasure1")
    val strmeasure1: String,
    @Expose
    @SerializedName("strMeasure2")
    val strmeasure2: String,
    @Expose
    @SerializedName("strMeasure3")
    val strmeasure3: String,
    @Expose
    @SerializedName("strMeasure4")
    val strmeasure4: String,
    @Expose
    @SerializedName("strMeasure5")
    val strmeasure5: String,
    @Expose
    @SerializedName("strMeasure6")
    val strmeasure6: String,
    @Expose
    @SerializedName("strMeasure7")
    val strmeasure7: String,
    @Expose
    @SerializedName("strMeasure8")
    val strmeasure8: String,
    @Expose
    @SerializedName("strMeasure9")
    val strmeasure9: String,
    @Expose
    @SerializedName("strMeasure10")
    val strmeasure10: String,
    @Expose
    @SerializedName("strMeasure11")
    val strmeasure11: String,
    @Expose
    @SerializedName("strMeasure12")
    val strmeasure12: String,
    @Expose
    @SerializedName("strMeasure13")
    val strmeasure13: String,
    @Expose
    @SerializedName("strMeasure14")
    val strmeasure14: String,
    @Expose
    @SerializedName("strMeasure15")
    val strmeasure15: String,
    @Expose
    @SerializedName("strMeasure16")
    val strmeasure16: String,
    @Expose
    @SerializedName("strMeasure17")
    val strmeasure17: String,
    @Expose
    @SerializedName("strMeasure18")
    val strmeasure18: String,
    @Expose
    @SerializedName("strMeasure19")
    val strmeasure19: String,
    @Expose
    @SerializedName("strMeasure20")
    val strmeasure20: String,
    @Expose
    @SerializedName("strSource")
    val strsource: String
)