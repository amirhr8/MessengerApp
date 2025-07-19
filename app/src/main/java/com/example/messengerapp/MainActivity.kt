package com.example.messengerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.messengerapp.ui.ChatListScreen
import com.example.messengerapp.ui.LoginScreen
import com.example.messengerapp.ui.ProfileScreen
import com.example.messengerapp.ui.SignupScreen
import com.example.messengerapp.ui.SingleChatScreen
import com.example.messengerapp.ui.SingleStatusScreen
import com.example.messengerapp.ui.StatusListScreen
import com.example.messengerapp.ui.theme.MessengerAppTheme


sealed class DestinationScreen(val route: String) {
    object Signup : DestinationScreen("signup")
    object Login : DestinationScreen("login")
    object Profile : DestinationScreen("profile")
    object ChatList : DestinationScreen("chatList")
    object SingleChat : DestinationScreen("singleChat/{chatId}") {
        fun createRoute(id: String) = "singleChat/$id"
    }

    object statusList : DestinationScreen("statusList")
    object singleStatus : DestinationScreen("singleStatus/{statusId}") {
        fun createRoute(id: String) = "singleStatus/$id"
    }

}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MessengerAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    chatAppNavigation()
                }
            }
        }
    }
}

@Composable
fun chatAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = DestinationScreen.Profile.route
    ) {
        composable(DestinationScreen.Signup.route) {
            SignupScreen()
        }

        composable(DestinationScreen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(DestinationScreen.statusList.route) {
            StatusListScreen(navController = navController)
        }

        composable(DestinationScreen.singleStatus.route) {
            SingleStatusScreen("123")
        }

        composable(DestinationScreen.ChatList.route) {
            ChatListScreen(navController = navController)
        }

        composable(DestinationScreen.SingleChat.route) {
            SingleChatScreen("123")
        }
    }
}