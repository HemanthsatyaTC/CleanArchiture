package com.hemanth.data.model


import com.google.gson.annotations.SerializedName

data class BiographyModel(
    @SerializedName("aliases")
    val aliases: List<String?>? = listOf(),
    @SerializedName("alignment")
    val alignment: String? = "",
    @SerializedName("alterEgos")
    val alterEgos: String? = "",
    @SerializedName("firstAppearance")
    val firstAppearance: String? = "",
    @SerializedName("fullName")
    val fullName: String? = "",
    @SerializedName("placeOfBirth")
    val placeOfBirth: String? = "",
    @SerializedName("publisher")
    val publisher: String? = ""
)