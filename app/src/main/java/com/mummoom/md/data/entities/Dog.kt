package com.mummoom.md.data.entities

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("dogBirth") val dogBirth: String,
    @SerializedName("dogIdx") val dogIdx: Int,
    @SerializedName("dogName") val dogName : String ,
    @SerializedName("dogSex") val dogSex: String,
    @SerializedName("dogType") val dogType : String,
    @SerializedName("surgery") val surgery: String
)


