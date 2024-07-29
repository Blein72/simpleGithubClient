package com.blein72.simplegithubclient.presentation.userDetails

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.blein72.simplegithubclient.data.model.UserDetail

const val USER_DETAIL_SCREEN_PATH = "users_list"

@Composable
fun UserDetailsScreen(
    userName: String = "",
    navController: NavController,
    viewModel: UserDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getUserDetail(userName)
    }

    UserDetailScreenContent(userDetail = state.userDetail,
        openUrl = { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        },
        backButtonClick = {
            navController.popBackStack()
        }
    )

    if (state.showLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }

    if (state.showErrorDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text(text = "Something went wrong. Tou can try to reload data")
                Button(
                    onClick = {
                        viewModel.getUserDetail(userName)
                        viewModel.hideErrorDialog()
                    }
                ) {
                    Text(text = "Reload")
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetailScreenContent(
    userDetail: UserDetail?,
    openUrl: (String) -> Unit,
    backButtonClick: () -> Unit
) {
    if (userDetail != null) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = { Text(text = "User Details") },
                navigationIcon = {
                    IconButton(onClick = { backButtonClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                fontSize = 24.sp,
                text = userDetail.login.orEmpty(),
                textAlign = TextAlign.Center
            )
            AsyncImage(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .clip(CircleShape),
                model = userDetail.avatarUrl,
                contentDescription = "avatar",
                alignment = Alignment.Center

            )
            ListTextItem("email :", userDetail.email)
            ListTextItem("Name :", userDetail.name)
            ListTextItem("Company:", userDetail.company)
            ListTextItem("Location:", userDetail.location)
            ListTextItem("Bio :", userDetail.bio)
            ClickableUrlStringListItem("Blog :", userDetail.blog, openUrl)
            ClickableUrlStringListItem("Repos :", userDetail.reposUrl, openUrl)
            ListTextItem("Public repos :", (userDetail.publicRepos ?: 0).toString())
            ListTextItem("Public gist :", (userDetail.publicGists ?: 0).toString())

        }
    }

}

@Composable
private fun ListTextItem(title: String, st: String?) {
    if (!st.isNullOrEmpty()) {
        SelectionContainer {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                )

                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    fontSize = 16.sp,
                    text = st
                )
            }
        }
    }
}

@Composable
private fun ClickableUrlStringListItem(
    title: String, st: String?,
    openUrl: (String) -> Unit
) {
    if (!st.isNullOrEmpty()) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
            )
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 16.sp,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(st)
                }
                addStringAnnotation(
                    tag = "URL",
                    annotation = st,
                    start = 0,
                    end = 22
                )
            }
            ClickableText(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = annotatedString,
                onClick = { offset ->
                    annotatedString.getStringAnnotations(
                        tag = "URL",
                        start = offset,
                        end = offset
                    )
                        .firstOrNull()?.let { annotation ->
                            openUrl(annotation.item)
                        }
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun UserDetailScreenContentPreview() {
    UserDetailScreenContent(
        userDetail = testUserDetail,
        openUrl = {},
        backButtonClick = {}
    )
}

private val testUserDetail = UserDetail(
    avatarUrl = null,
    bio = "biography",
    blog = "blog url",
    company = "Company name",
    createdAt = null,
    email = "email",
    eventsUrl = null,
    followers = null,
    followersUrl = null,
    following = null,
    followingUrl = null,
    gistsUrl = null,
    gravatarId = null,
    hireable = null,
    htmlUrl = null,
    id = null,
    location = "location",
    login = "UserName",
    name = "Real Name",
    nodeId = null,
    organizationsUrl = null,
    publicGists = 17,
    publicRepos = 10,
    receivedEventsUrl = null,
    reposUrl = "reposUrl",
    siteAdmin = null,
    starredUrl = null,
    subscriptionsUrl = null,
    twitterUsername = null,
    type = null,
    updatedAt = null,
    url = null
)