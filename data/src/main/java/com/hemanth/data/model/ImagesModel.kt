package com.hemanth.data.model


import com.google.gson.annotations.SerializedName

data class ImagesModel(
    @SerializedName("lg")
    val lg: String? = "",
    @SerializedName("md")
    val md: String? = "",
    @SerializedName("sm")
    val sm: String? = "",
    @SerializedName("xs")
    val xs: String? = ""
)