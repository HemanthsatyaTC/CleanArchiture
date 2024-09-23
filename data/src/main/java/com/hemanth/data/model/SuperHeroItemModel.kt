package com.hemanth.data.model


import com.google.gson.annotations.SerializedName

data class SuperHeroItemModel(
    @SerializedName("appearance")
    val appearance: AppearanceModel? = AppearanceModel(),
    @SerializedName("biography")
    val biography: BiographyModel? = BiographyModel(),
    @SerializedName("connections")
    val connections: ConnectionsModel? = ConnectionsModel(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("images")
    val images: ImagesModel? = ImagesModel(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("powerstats")
    val powerstats: PowerstatsModel? = PowerstatsModel(),
    @SerializedName("slug")
    val slug: String? = "",
    @SerializedName("work")
    val work: WorkModel? = WorkModel()
)