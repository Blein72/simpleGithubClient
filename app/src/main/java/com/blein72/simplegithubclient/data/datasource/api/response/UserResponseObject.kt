package com.blein72.simplegithubclient.data.datasource.api.response


import com.google.gson.annotations.SerializedName

data class UserResponseObject(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("url")
    val url: String?
)