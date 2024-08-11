package com.blein72.simplegithubclient.testdata

import com.blein72.simplegithubclient.data.datasource.api.response.UserResponseObject
import com.blein72.simplegithubclient.data.datasource.api.response.UserDetailResponseObject
import com.blein72.simplegithubclient.data.model.UserData
import com.blein72.simplegithubclient.data.model.UserDetailData

val TEST_USER_RESPONSE_DATA = UserResponseObject(
    avatarUrl = "avatarUrl",
    login = "Name",
    url = "profileUrl"
)

val TEST_USER_DATA = UserData(
    avatarUrl = "avatarUrl",
    login = "Name",
    url = "profileUrl"
)

val TEST_USER_RESPONSE_LIST_DATA =listOf(
    TEST_USER_RESPONSE_DATA.copy(
        login = "Name1",
        url = "ProfileUrl1"
    ),
    TEST_USER_RESPONSE_DATA.copy(
        login = "Name2",
        url = "ProfileUrl2"
    ),
    TEST_USER_RESPONSE_DATA.copy(
        login = "Name3",
        url = "ProfileUrl3"
    ),
    TEST_USER_RESPONSE_DATA.copy(
        login = "Name4",
        url = "ProfileUrl4"
    )
)

val TEST_USER_LIST_DATA =listOf(
    TEST_USER_DATA.copy(
        login = "Name1",
        url = "ProfileUrl1"
    ),
    TEST_USER_DATA.copy(
        login = "Name2",
        url = "ProfileUrl2"
    ),
    TEST_USER_DATA.copy(
        login = "Name3",
        url = "ProfileUrl3"
    ),
    TEST_USER_DATA.copy(
        login = "Name4",
        url = "ProfileUrl4"
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
