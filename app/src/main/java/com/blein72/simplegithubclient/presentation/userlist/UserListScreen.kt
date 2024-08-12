package com.blein72.simplegithubclient.presentation.userlist

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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.blein72.simplegithubclient.R
import com.blein72.simplegithubclient.data.model.UserData
import com.blein72.simplegithubclient.presentation.composable.ErrorDialog
import com.blein72.simplegithubclient.presentation.composable.ScreenLoadingIndicator
import com.blein72.simplegithubclient.presentation.userDetails.USER_DETAIL_SCREEN_PATH


const val USER_LIST_SCREEN_PATH = "users_list"

@Composable
fun UserListScreen(
    navController: NavHostController,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getInitialData()
    }

    val listState = rememberLazyListState()

    // Check if the user has scrolled to the end
    val isEndReached by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - 1
        }
    }

    // Load more data when end is reached
    LaunchedEffect(isEndReached) {
        if (isEndReached) {
            viewModel.loadNextPage()
        }
    }

    UserListScreenContent(
        listState = listState,
        showListLoading = state.showListItemsLoading,
        userList = state.userList,
        onItemClicked = { name ->
            navController.navigate("$USER_DETAIL_SCREEN_PATH/$name")
        }
    )

    if (state.showScreenLoading) {
        ScreenLoadingIndicator()
    }

    if (state.showErrorDialog) {
        ErrorDialog(
            errorText = state.errorMessage.orEmpty(),
            onReloadClicked = {
                viewModel.reloadCurrentPage()
                viewModel.hideErrorDialog()
            }
        )
    }
}

@Composable
private fun UserListScreenContent(
    listState: LazyListState,
    userList: List<UserData>,
    showListLoading: Boolean,
    onItemClicked: (String?) -> Unit
) {

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(userList.size) { index ->
            val item = userList[index]
            UserListItem(
                user = item,
                onItemClicked = onItemClicked
            )
        }
        if (showListLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(start = 16.dp, end = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun UserListItem(
    user: UserData,
    onItemClicked: (String?) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
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
            contentDescription = stringResource(R.string.user_list_content_description_avatar),
            alignment = Alignment.Center
        )
        Column {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp),
                text = user.login
            )
            Text(
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, bottom = 10.dp),
                text = user.url
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
        onItemClicked = {},
        listState = rememberLazyListState(),
        showListLoading = true
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

private val testUserData = UserData(
    avatarUrl = "avatarUrl",
    login = "Name",
    url = "profileUrl",
    id = 1
)
