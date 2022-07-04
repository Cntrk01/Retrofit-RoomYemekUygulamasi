package com.example.foodrecipeapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp.adapter.MainCategoryAdapter
import com.example.foodrecipeapp.adapter.SubCategoryAdapter
import com.example.foodrecipeapp.database.RecipeDatabase
import com.example.foodrecipeapp.entities.CategoryItems
import com.example.foodrecipeapp.entities.MealItems
import com.example.foodrecipeapp.entities.Recipies
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {
    var arrMainCategory=ArrayList<CategoryItems>()
    var arrSubbCategory=ArrayList<MealItems>()
    var mainCategoryAdapter=MainCategoryAdapter()
    var subCategoryAdapter= SubCategoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getDataFromDB()
        mainCategoryAdapter.setClickListener(onClicked)
        subCategoryAdapter.setClickListener(onClickedSubItem)
    }
    private val onClicked = object : MainCategoryAdapter.OnItemClickListener{
        override fun OnClicked(categoryName: String) {
            getMealDataFromDB(categoryName)
        }
    }
       private val onClickedSubItem = object : SubCategoryAdapter.OnItemClickListener{
           override fun OnClicked(id: String) {
               var intent=Intent(this@HomeActivity,DetailActivity::class.java)
               intent.putExtra("id",id)
               startActivity(intent)
           }
       }

    private fun getDataFromDB(){
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()
                mainCategoryAdapter.setData(arrMainCategory)
                rv_main_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_main_category.adapter = mainCategoryAdapter
                getMealDataFromDB(arrMainCategory[0].strcategory)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getMealDataFromDB(categoryName:String){
        tvCategory.text = "$categoryName Category"
        launch {
            this.let {
                val cat=RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrSubbCategory= cat as ArrayList<MealItems>
                subCategoryAdapter.setData(arrSubbCategory)
                rv_sub_category.layoutManager=LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_sub_category.adapter=subCategoryAdapter
            }
        }
    }
}