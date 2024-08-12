package com.blein72.core.data

import com.blein72.core.data.response.UserResponseObject

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
