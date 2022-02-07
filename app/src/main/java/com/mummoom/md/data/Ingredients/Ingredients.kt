package com.mummoom.md.data.Ingredients

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Ingredients(
    @SerializedName("name") val name: String,
    @SerializedName("category") val category: String,
    @SerializedName("imgUrl") val imgUrl: String,
    @SerializedName("kcal") val kcal: Int,
    @SerializedName("warning") val warning: String,
    @SerializedName("spec") val spec: String,
    @SerializedName("score") val score: Int,
    @SerializedName("component") val component: Component,
    @SerializedName("ingredietnsIdx") val ingredietnsIdx: Int,
) : Serializable
