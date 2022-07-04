package com.example.foodrecipeapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.foodrecipeapp.entities.MealResponse
import com.example.foodrecipeapp.retrofitclient.GetDataService
import com.example.foodrecipeapp.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : BaseActivity() {
    var youtubeLink = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var idd=intent.getStringExtra("id")

        getSpesificItem(idd!!)

        imgToolbarBtnBack.setOnClickListener {
            finish()
        }
        btnYoutube.setOnClickListener {
            val uri = Uri.parse(youtubeLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    fun getSpesificItem(id:String){
        val service= RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call=service.getSpecificItem(id)
        call.enqueue(object : Callback<MealResponse> {
            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {
                Glide.with(this@DetailActivity).load(response.body()!!.mealsEntity[0].strmealthumb).into(imgItem)

                tvCategory.text = response.body()!!.mealsEntity[0].strmeal

                var ingredient = "${response.body()!!.mealsEntity[0].strıngredient1}      ${response.body()!!.mealsEntity[0].strmeasure1}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient2}      ${response.body()!!.mealsEntity[0].strmeasure2}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient3}      ${response.body()!!.mealsEntity[0].strmeasure3}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient4}      ${response.body()!!.mealsEntity[0].strmeasure4}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient5}      ${response.body()!!.mealsEntity[0].strmeasure5}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient6}      ${response.body()!!.mealsEntity[0].strmeasure6}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient7}      ${response.body()!!.mealsEntity[0].strmeasure7}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient8}      ${response.body()!!.mealsEntity[0].strmeasure8}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient9}      ${response.body()!!.mealsEntity[0].strmeasure9}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient10}      ${response.body()!!.mealsEntity[0].strmeasure10}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient11}      ${response.body()!!.mealsEntity[0].strmeasure11}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient12}      ${response.body()!!.mealsEntity[0].strmeasure12}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient13}      ${response.body()!!.mealsEntity[0].strmeasure13}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient14}      ${response.body()!!.mealsEntity[0].strmeasure14}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient15}      ${response.body()!!.mealsEntity[0].strmeasure15}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient16}      ${response.body()!!.mealsEntity[0].strmeasure16}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient17}      ${response.body()!!.mealsEntity[0].strmeasure17}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient18}      ${response.body()!!.mealsEntity[0].strmeasure18}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient19}      ${response.body()!!.mealsEntity[0].strmeasure19}\n" +
                        "${response.body()!!.mealsEntity[0].strıngredient20}      ${response.body()!!.mealsEntity[0].strmeasure20}\n"

                tvIngredients.text = ingredient
                tvInstructions.text = response.body()!!.mealsEntity[0].strınstructions

                if (response.body()!!.mealsEntity[0].strsource != null){
                    youtubeLink = response.body()!!.mealsEntity[0].strsource
                }else{
                    btnYoutube.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Toast.makeText(this@DetailActivity,"Something Went Wrong", Toast.LENGTH_LONG).show()
                loader.visibility= View.INVISIBLE
            }


        })
    }

}