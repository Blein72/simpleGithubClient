package com.blein72.feature_users.testdata

import com.blein72.core.data.UserDetailData
import com.blein72.core.data.response.UserDetailResponseObject
import com.blein72.core.data.response.UserResponseObject

val TEST_USER_RESPONSE_DATA = UserResponseObject(
    avatarUrl = "avatarUrl",
    login = "Name",
    url = "profileUrl",
    id = 1
)

val TEST_USER_RESPONSE_LIST_DATA = listOf(
    TEST_USER_RESPONSE_DATA.copy(
        login = "Name1",
        url = "ProfileUrl1",
        id = 1
    ),
    TEST_USER_RESPONSE_DATA.copy(
        login = "Name2",
        url = "ProfileUrl2",
        id = 2
    ),
    TEST_USER_RESPONSE_DATA.copy(
        login = "Name3",
        url = "ProfileUrl3",
        id = 3
    ),
    TEST_USER_RESPONSE_DATA.copy(
        login = "Name4",
        url = "ProfileUrl4",
        id = 4
    )
)

val TEST_USER_DETAILS_RESPONSE_DATA = UserDetailResponseObject(
    avatarUrl = "avatarUrl",
    bio = "biography",
    blog = "blog url",
    company = "Company name",
    email = "email",
    location = "location",
    login = "UserName",
    name = "Real Name",
    publicGists = 17,
    publicRepos = 10,
    reposUrl = "reposUrl",
    url = "url"
)

val TEST_USER_DETAILS_DATA = UserDetailData(
    avatarUrl = "avatarUrl",
    bio = "biography",
    blog = "blog url",
    company = "Company name",
    email = "email",
    location = "location",
    login = "UserName",
    name = "Real Name",
    publicGists = 17,
    publicRepos = 10,
    reposUrl = "reposUrl",
    url = "url"
)

val TEST_USER_DETAILS_DATA_LOCAL = TEST_USER_DETAILS_DATA.copy(
    login = "differentLogin",
    bio = "newBio"
)
