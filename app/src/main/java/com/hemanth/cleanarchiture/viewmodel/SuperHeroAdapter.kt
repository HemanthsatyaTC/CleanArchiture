package com.hemanth.cleanarchiture.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.data.model.shopping.ShoppingDataItemModel
import com.hemanth.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SuperHeroAdapter @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _upcomingData = mutableStateOf<List<ShoppingDataItemModel>>(emptyList())
    val upcomingData: State<List<ShoppingDataItemModel>> = _upcomingData

    init {
        fetchDetails()

    }

    fun fetchDetails() {
        viewModelScope.launch {
            try {
                val details = repository.getDetails()
                _upcomingData.value = details as List<ShoppingDataItemModel>
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching data: ${e.message}")
            }
        }
    }

    fun getProductsByCategory(category: String): List<ShoppingDataItemModel> {
        return upcomingData.value.filter { it.category == category }
    }
}