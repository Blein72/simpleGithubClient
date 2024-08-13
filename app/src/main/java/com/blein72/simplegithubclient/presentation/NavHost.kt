package com.blein72.simplegithubclient.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.blein72.core.util.USER_DETAIL_SCREEN_PATH
import com.blein72.core.util.USER_LIST_SCREEN_PATH
import com.blein72.feature_users.presentation.userlist.UserListScreen
import com.blein72.feature_users.presentation.userDetails.UserDetailsScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = USER_LIST_SCREEN_PATH) {
        composable(USER_LIST_SCREEN_PATH) {
            UserListScreen(navController)
        }
        composable(
            route = "${USER_DETAIL_SCREEN_PATH}/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) {navBackStackEntry ->
            UserDetailsScreen(
                userName = navBackStackEntry.arguments?.getString("userName").orEmpty(),
                navController = navController
            )
        }
    }
}