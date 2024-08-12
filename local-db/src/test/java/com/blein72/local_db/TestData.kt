package com.blein72.local_db

import com.blein72.local_db.entity.UserDataEntity

val TEST_USER_DATA_ENTITY = UserDataEntity(
    avatarUrl = "avatarUrl",
    login = "Name",
    url = "profileUrl",
    id = 1
)

val TEST_USER_LIST_DATA_ENTITY = listOf(
    TEST_USER_DATA_ENTITY.copy(
        login = "Name1",
        url = "ProfileUrl1",
        id = 1
    ),
    TEST_USER_DATA_ENTITY.copy(
        login = "Name2",
        url = "ProfileUrl2",
        id = 2
    ),
    TEST_USER_DATA_ENTITY.copy(
        login = "Name3",
        url = "ProfileUrl3",
        id = 3
    ),
    TEST_USER_DATA_ENTITY.copy(
        login = "Name4",
        url = "ProfileUrl4",
        id = 4
    )
)
