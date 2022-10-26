package com.example.applogin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.applogin.navigations.AppNavigation
//import com.example.applogin.navigations.Destinations
//import com.example.applogin.presentation.home.HomeScreen
//import com.example.applogin.presentation.login.LoginScreen
//import com.example.applogin.presentation.registration.RegistrationScreen
import com.example.applogin.ui.theme.AppLoginTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppLoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppLoginTheme {
        AppNavigation()
    }
}