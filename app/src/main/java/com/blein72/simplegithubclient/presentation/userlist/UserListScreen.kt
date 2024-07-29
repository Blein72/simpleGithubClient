package com.blein72.simplegithubclient.presentation.userlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.blein72.simplegithubclient.data.model.User
import com.blein72.simplegithubclient.presentation.userDetails.USER_DETAIL_SCREEN_PATH


const val USER_LIST_SCREEN_PATH = "users_list"

@Composable
fun UserListScreen(
    navController: NavHostController,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getUserList()
    }

    UserListScreenContent(
        userList = state.userList,
        onItemClicked = { name ->
            navController.navigate("$USER_DETAIL_SCREEN_PATH/$name")
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
                Text(text = "Something went wrong: ${state.errorMessage.orEmpty()}. Tou can try to reload data")
                Button(
                    onClick = {
                        viewModel.getUserList()
                        viewModel.hideErrorDialog()
                    }
                ) {
                    Text(text = "Reload")
                }
            }
        }
    }
}

@Composable
private fun UserListScreenContent(
    userList: List<User>,
    onItemClicked: (String?) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        items(userList.size) { index ->
            val item = userList[index]
            UserListItem(
                user = item,
                onItemClicked = onItemClicked
            )
        }
    }
}

@Composable
private fun UserListItem(
    user: User,
    onItemClicked: (String?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onItemClicked(user.login)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(CircleShape),
            model = user.avatarUrl,
            contentDescription = "avatar",
            alignment = Alignment.Center
        )
        Column() {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp),
                text = user.login.orEmpty()
            )
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, bottom = 10.dp),
                text = user.url.orEmpty()
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun UserListScreenPreview() {
    UserListScreenContent(
        userList = listOf(
            testUserData.copy(
                login = "Name1",
                url = "ProfileUrl1"
            ),
            testUserData.copy(
                login = "Name2",
                url = "ProfileUrl2"
            ),
            testUserData.copy(
                login = "Name3",
                url = "ProfileUrl3"
            ),
            testUserData.copy(
                login = "Name4",
                url = "ProfileUrl4"
            )
        ),
        onItemClicked = {}
    )
}

@Composable
@Preview(showBackground = true)
private fun UserListItemPreview() {
    UserListItem(
        user = testUserData,
        onItemClicked = {}
    )
}

private val testUserData = User(
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
