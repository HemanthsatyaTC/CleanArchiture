package com.hemanth.data.model


import com.google.gson.annotations.SerializedName

data class AppearanceModel(
    @SerializedName("eyeColor")
    val eyeColor: String? = "",
    @SerializedName("gender")
    val gender: String? = "",
    @SerializedName("hairColor")
    val hairColor: String? = "",
    @SerializedName("height")
    val height: List<String?>? = listOf(),
    @SerializedName("race")
    val race: String? = "",
    @SerializedName("weight")
    val weight: List<String?>? = listOf()
)