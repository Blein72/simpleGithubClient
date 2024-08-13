package com.blein72.local_db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blein72.core.data.UserDetailData

@Entity(tableName = "user_detail_entity_table")
data class UserDataDetailEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Int = 0,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "bio")
    val bio: String,

    @ColumnInfo(name = "blog")
    val blog: String,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "repos_url")
    val reposUrl: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "login")
    val login: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "public_gists")
    val publicGists: Int,

    @ColumnInfo(name = "public_repos")
    val publicRepos: Int,
)

fun UserDataDetailEntity.toUserDetailData() = UserDetailData(
    avatarUrl = avatarUrl,
    bio = bio,
    blog = blog,
    company = company,
    email = email,
    location = location,
    reposUrl = reposUrl,
    url = url,
    login = login,
    name = name,
    publicGists = publicGists,
    publicRepos = publicRepos
)

fun UserDetailData.toUserDetailEntity() = UserDataDetailEntity(
    avatarUrl = avatarUrl,
    bio = bio,
    blog = blog,
    company = company,
    email = email,
    location = location,
    reposUrl = reposUrl,
    url = url,
    login = login,
    name = name,
    publicGists = publicGists,
    publicRepos = publicRepos
)
