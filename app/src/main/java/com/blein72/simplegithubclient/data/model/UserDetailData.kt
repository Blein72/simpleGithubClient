package com.blein72.simplegithubclient.data.model

import com.blein72.simplegithubclient.data.datasource.remote.api.response.UserDetailResponseObject

data class UserDetailData(
    val avatarUrl: String,
    val bio: String,
    val blog: String,
    val company: String,
    val email: String,
    val location: String,
    val reposUrl: String,
    val url: String,
    val login: String,
    val name: String,
    val publicGists: Int,
    val publicRepos: Int,
)

fun UserDetailResponseObject.toUserDetailData() = UserDetailData(
    avatarUrl = avatarUrl.orEmpty(),
    name = name.orEmpty(),
    url = url.orEmpty(),
    bio = bio.orEmpty(),
    blog = blog.orEmpty(),
    company = company.orEmpty(),
    email = email.orEmpty(),
    location = location.orEmpty(),
    reposUrl = reposUrl.orEmpty(),
    login = login.orEmpty(),
    publicGists = publicGists ?: 0,
    publicRepos = publicRepos ?: 0

)