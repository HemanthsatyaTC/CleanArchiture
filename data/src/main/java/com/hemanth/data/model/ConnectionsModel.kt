package com.hemanth.data.model


import com.google.gson.annotations.SerializedName

data class ConnectionsModel(
    @SerializedName("groupAffiliation")
    val groupAffiliation: String? = "",
    @SerializedName("relatives")
    val relatives: String? = ""
)