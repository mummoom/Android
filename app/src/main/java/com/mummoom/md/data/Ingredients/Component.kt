package com.mummoom.md.data.Ingredients

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Component(
    @SerializedName("tan") val tan: Float,
    @SerializedName("dan") val dan: Float,
    @SerializedName("gi") val gi: Float,
    @SerializedName("water") val water: Float,
    @SerializedName("mu") val mu: Float,
    @SerializedName("effect") val effect: String,
    @SerializedName("componentIdx") val componentIdx: Int,
): Serializable