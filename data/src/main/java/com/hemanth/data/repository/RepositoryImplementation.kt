package com.hemanth.data.repository

import com.hemanth.data.model.SuperHeroItemModel
import com.hemanth.data.remote.SuperHeroInterface
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    val apiHero: SuperHeroInterface
): Repository {
    override suspend fun getDetails(): List<SuperHeroItemModel> {
        return apiHero.getDetails()
    }
}