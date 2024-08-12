package com.blein72.simplegithubclient.data.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blein72.simplegithubclient.data.model.UserData

@Entity(tableName = "user_entity_table")
data class UserDataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Int,

    @ColumnInfo(name = "user_avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "user_login")
    val login: String,

    @ColumnInfo(name = "user_url")
    val url: String,
)

fun UserDataEntity.toUserData() = UserData(
    id = id, avatarUrl = avatarUrl, login = login, url = url
)

fun UserData.toUserDataEntity() = UserDataEntity(
    id = id, avatarUrl = avatarUrl, login = login, url = url
)
