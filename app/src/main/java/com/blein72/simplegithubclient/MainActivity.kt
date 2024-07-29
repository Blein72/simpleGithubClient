package com.blein72.simplegithubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.blein72.simplegithubclient.presentation.AppNavHost
import com.blein72.simplegithubclient.presentation.theme.SimpleGithubClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleGithubClientTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    val navHostController = rememberNavController()
                    Column(modifier = Modifier
                        .padding(padding)) {
                        AppNavHost(navHostController)
                    }

                }
            }
        }
    }
}