package com.blein72.simplegithubclient.presentation.userDetails

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.blein72.simplegithubclient.R
import com.blein72.simplegithubclient.data.model.UserDetailData

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
                Text(
                    text = stringResource(
                        R.string.something_went_wrong_tou_can_try_to_reload_data_with_error,
                        state.errorMessage.orEmpty()
                    )
                )
                Button(
                    onClick = {
                        viewModel.getUserDetail(userName)
                        viewModel.hideErrorDialog()
                    }
                ) {
                    Text(text = stringResource(R.string.basic_dialog_error_reload))
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetailScreenContent(
    userDetail: UserDetailData?,
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
                title = { Text(text = stringResource(R.string.user_details_top_bar_title)) },
                navigationIcon = {
                    IconButton(onClick = { backButtonClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.user_details_content_description_back)
                        )
                    }
                }
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 24.sp,
                text = userDetail.login,
                textAlign = TextAlign.Center
            )
            AsyncImage(
                modifier = Modifier
                    .padding(10.dp)
                    .width(200.dp)
                    .height(200.dp)
                    .clip(CircleShape),

                model = userDetail.avatarUrl,
                contentDescription = stringResource(R.string.user_details_content_description_avatar),
                alignment = Alignment.Center

            )
            ListTextItem(stringResource(R.string.user_details_email), userDetail.email)
            ListTextItem(stringResource(R.string.user_details_name), userDetail.name)
            ListTextItem(stringResource(R.string.user_details_company), userDetail.company)
            ListTextItem(stringResource(R.string.user_details_location), userDetail.location)
            ListTextItem(stringResource(R.string.user_details_bio), userDetail.bio)
            ClickableUrlStringListItem(
                stringResource(R.string.user_details_blog),
                userDetail.blog,
                openUrl
            )
            ClickableUrlStringListItem(
                stringResource(R.string.user_details_repos),
                userDetail.reposUrl,
                openUrl
            )
            ListTextItem(
                stringResource(R.string.user_details_public_repos),
                (userDetail.publicRepos).toString()
            )
            ListTextItem(
                stringResource(R.string.user_details_public_gist),
                (userDetail.publicGists).toString()
            )

        }
    }

}

@Composable
private fun ListTextItem(
    title: String,
    st: String
) {
    if (st.isNotEmpty()) {
        SelectionContainer {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
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
    title: String,
    st: String?,
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
                        textDecoration = TextDecoration.Underline,
                        color = LocalContentColor.current
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

private val testUserDetail = UserDetailData(
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
    url = "userUrl"
)