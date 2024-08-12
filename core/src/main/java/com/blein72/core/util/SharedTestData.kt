package com.blein72.core.util

import com.blein72.core.data.UserData

val TEST_USER_DATA = UserData(
    avatarUrl = "avatarUrl",
    login = "Name",
    url = "profileUrl",
    id = 1
)

val TEST_USER_LIST_DATA = listOf(
    TEST_USER_DATA.copy(
        login = "Name1",
        url = "ProfileUrl1",
        id = 1
    ),
    TEST_USER_DATA.copy(
        login = "Name2",
        url = "ProfileUrl2",
        id = 2
    ),
    TEST_USER_DATA.copy(
        login = "Name3",
        url = "ProfileUrl3",
        id = 3
    ),
    TEST_USER_DATA.copy(
        login = "Name4",
        url = "ProfileUrl4",
        id = 4
    )
)