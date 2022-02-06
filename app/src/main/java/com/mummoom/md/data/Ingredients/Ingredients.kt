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
) : Serializable
{
    @PrimaryKey(autoGenerate = true) var ingredientsIdx: Int = 0
//    @PrimaryKey(autoGenerate = true) var component_idx: Int = 0
}
