package com.hemanth.data.repository

import com.hemanth.data.model.SuperHeroItemModel

interface Repository {
    suspend fun getDetails(): List<SuperHeroItemModel>
}