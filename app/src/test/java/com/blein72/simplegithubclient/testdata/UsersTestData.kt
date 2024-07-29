package com.blein72.simplegithubclient.testdata

import com.blein72.simplegithubclient.data.model.User
import com.blein72.simplegithubclient.data.model.UserDetail

val TEST_USER_DATA = User(
    avatarUrl = null,
    eventsUrl = null,
    followersUrl = null,
    followingUrl = null,
    gistsUrl = null,
    gravatarId = null,
    htmlUrl = null,
    id = null,
    login = "Name",
    nodeId = null,
    organizationsUrl = null,
    receivedEventsUrl = null,
    reposUrl = null,
    siteAdmin = null,
    starredUrl = null,
    subscriptionsUrl = null,
    type = null,
    url = "profileUrl"
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

val TEST_USER_DETAILS_DATA = UserDetail(
    avatarUrl = "avatarUrl",
    bio = "biography",
    blog = "blog url",
    company = "Company name",
    createdAt = "createdAt",
    email = "email",
    eventsUrl = "eventsUrl",
    followers = 10,
    followersUrl = "followersUrl",
    following = 12,
    followingUrl = "followingUrl",
    gistsUrl = "gistsUrl",
    gravatarId = "gravatarId",
    hireable = false,
    htmlUrl = "htmlUrl",
    id = 100500,
    location = "location",
    login = "UserName",
    name = "Real Name",
    nodeId = "nodeIn",
    organizationsUrl = "organizationUrl",
    publicGists = 17,
    publicRepos = 10,
    receivedEventsUrl = "receivedUrls",
    reposUrl = "reposUrl",
    siteAdmin = false,
    starredUrl = "starredUrl",
    subscriptionsUrl = "subscriptionsUrl",
    twitterUsername = "twitterUsername",
    type = "type",
    updatedAt = "updatedAt",
    url = "url"
)
