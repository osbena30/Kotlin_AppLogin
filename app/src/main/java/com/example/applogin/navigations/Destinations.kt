package com.example.applogin.navigations

import androidx.navigation.NamedNavArgument

sealed class Destinations(
    val route : String,
    val arguments : List<NamedNavArgument>
){
    object LoginScreen : Destinations("LoginScreen", emptyList())
    object RegistrationScreen : Destinations("RegistrationScreen", emptyList())
    object HomeScreen : Destinations("HomeScreen", emptyList())
}
