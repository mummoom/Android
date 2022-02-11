package com.mummoom.md.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WIthdrawUser(
    @SerializedName("withdrawPassword") val withdrawPassword : String,
    @SerializedName("withdrawReason") val withdrawReason : String
) : Serializable
