package com.example.foodrecipeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.foodrecipeapp.database.RecipeDatabase
import com.example.foodrecipeapp.entities.Category
import com.example.foodrecipeapp.entities.Meal
import com.example.foodrecipeapp.entities.MealItems
import com.example.foodrecipeapp.retrofitclient.GetDataService
import com.example.foodrecipeapp.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity()
    ,EasyPermissions.RationaleCallbacks,EasyPermissions.PermissionCallbacks {
   private var READ_STORAGE_PERM=123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        readStorageTask()
        btnGetStarted.setOnClickListener {
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    fun getCategory(){
        val service=RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call=service.getCategoriList()
        call.enqueue(object : Callback<Category>{
            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {
                for(arr in response.body()!!.categorieitems!!){
                    getMeal(arr.strcategory)
                }
                insertDataIntoRoomDb(response.body())
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(this@SplashActivity,"Something Went Wrong",Toast.LENGTH_LONG).show()
                loader.visibility= View.INVISIBLE
            }


        })
    }
    fun getMeal(categoryName:String){
        val service=RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call=service.getMealList(categoryName)
        call.enqueue(object : Callback<Meal>{
            override fun onResponse(
                call: Call<Meal>,
                response: Response<Meal>
            ) {
                insertMealDataIntoRoomDb(categoryName,response.body())
            }

            override fun onFailure(call: Call<Meal >, t: Throwable) {
                Toast.makeText(this@SplashActivity,"Something Went Wrong",Toast.LENGTH_LONG).show()
                loader.visibility= View.INVISIBLE
            }


        })
    }
    fun insertDataIntoRoomDb(category:Category?){
        launch {
            this.let {
                for (arr in category!!.categorieitems!!){ //Bu satırı neden boyle yaptıgımızı anlamadım.
                    RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().insertCategory(arr)
                }

            }
        }
    }

    fun insertMealDataIntoRoomDb(categoryName: String,meal:Meal?){
        launch {
            this.let {
                for (arr in meal!!.mealitem!!){
                    var mealItemModel=MealItems(
                        arr.id,
                        arr.idMeal,
                        categoryName,
                        arr.strMeal,
                        arr.strMealThumb
                    )

                    RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().insertMeal(mealItemModel)
                    Log.d("mealData",arr.toString())
                }

                btnGetStarted.visibility=View.VISIBLE
            }
        }
    }

    fun clearDatabase(){
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().cleardb()
            }
        }
    }

    private fun hashReadStoragePermission():Boolean{
        return EasyPermissions.hasPermissions(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)

    }

    private fun readStorageTask(){
        if(hashReadStoragePermission()){
            clearDatabase()
           getCategory()
        }else{
            EasyPermissions.requestPermissions(
                this,
                "This App Need Acces To You Storage",
                READ_STORAGE_PERM,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }
    override fun onRationaleAccepted(requestCode: Int) {

    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}