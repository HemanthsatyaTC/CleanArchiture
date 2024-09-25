package com.hemanth.data.repository

import com.hemanth.data.model.shopping.ShoppingDataItemModel
import com.hemanth.data.remote.SuperHeroInterface
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    val apiHero: SuperHeroInterface
): Repository {
    override suspend fun getDetails(): List<ShoppingDataItemModel> {
        return apiHero.getDetails()
    }
}