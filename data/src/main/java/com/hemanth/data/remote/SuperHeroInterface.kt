package com.hemanth.data.remote

import com.hemanth.data.model.shopping.ShoppingDataItemModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SuperHeroInterface {
    @GET(SuperHeroApiDetails.END_POINTS)
    suspend fun getDetails(): List<ShoppingDataItemModel>
}