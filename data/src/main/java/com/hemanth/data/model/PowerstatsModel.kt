package com.hemanth.data.model


import com.google.gson.annotations.SerializedName

data class PowerstatsModel(
    @SerializedName("combat")
    val combat: Int? = 0,
    @SerializedName("durability")
    val durability: Int? = 0,
    @SerializedName("intelligence")
    val intelligence: Int? = 0,
    @SerializedName("power")
    val power: Int? = 0,
    @SerializedName("speed")
    val speed: Int? = 0,
    @SerializedName("strength")
    val strength: Int? = 0
)