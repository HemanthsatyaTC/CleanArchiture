package com.hemanth.data.repository

import com.hemanth.data.model.shopping.ShoppingDataItemModel

interface Repository {
    suspend fun getDetails(): List<ShoppingDataItemModel>
}