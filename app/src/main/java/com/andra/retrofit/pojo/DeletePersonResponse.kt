package com.andra.retrofit.pojo

import com.google.gson.annotations.SerializedName

data class DeletePersonResponse(
    @SerializedName("result")
    val result: String
)