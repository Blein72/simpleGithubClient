package com.blein72.simplegithubclient.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.blein72.simplegithubclient.presentation.userDetails.USER_DETAIL_SCREEN_PATH
import com.blein72.simplegithubclient.presentation.userDetails.UserDetailsScreen
import com.blein72.simplegithubclient.presentation.userlist.USER_LIST_SCREEN_PATH
import com.blein72.simplegithubclient.presentation.userlist.UserListScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = USER_LIST_SCREEN_PATH) {
        composable(USER_LIST_SCREEN_PATH) {
            UserListScreen(navController)
        }
        composable(
            route = "$USER_DETAIL_SCREEN_PATH/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) {navBackStackEntry ->
            UserDetailsScreen(
                userName = navBackStackEntry.arguments?.getString("userName").orEmpty(),
                navController = navController)
        }
    }
}