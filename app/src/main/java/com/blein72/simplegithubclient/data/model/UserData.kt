package com.blein72.simplegithubclient.data.model

import com.blein72.simplegithubclient.data.datasource.api.response.UserResponseObject

data class UserData(
    val id: Int,
    val avatarUrl: String,
    val login: String,
    val url: String,
)

fun List<UserResponseObject>?.toUserDataList(): List<UserData> =
    this?.map { it.toUserData() }.orEmpty()

fun UserResponseObject.toUserData() = UserData(
    avatarUrl = avatarUrl.orEmpty(),
    login = login.orEmpty(),
    url = url.orEmpty(),
    id = id ?: 0
)
