package com.mummoom.md.data.Ingredients

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Ingredients(
    @SerializedName("name") val name: String,
    @SerializedName("category") val category: String,
    @SerializedName("kcal") val kcal: Int,
    @SerializedName("warning") val warning: String,
    @SerializedName("spec") val spec: String,
    @SerializedName("img_url") val img_url: String,
    @SerializedName("score") val score: Int,

){
    @PrimaryKey(autoGenerate = true) var ingredients_idx: Int = 0
    @PrimaryKey(autoGenerate = true) var component_idx: Int = 0

}
