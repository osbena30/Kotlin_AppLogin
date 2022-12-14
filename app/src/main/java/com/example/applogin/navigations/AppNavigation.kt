package com.example.applogin.navigations
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.applogin.presentation.home.HomeScreen
import com.example.applogin.presentation.login.LoginScreen
import com.example.applogin.presentation.login.LoginViewModel
import com.example.applogin.presentation.registration.RegisterViewModel
import com.example.applogin.presentation.registration.RegistrationScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@ExperimentalAnimationApi
@Composable

fun AppNavigation(){
    val navController = rememberAnimatedNavController()
    BoxWithConstraints() {
        AnimatedNavHost(
            navController = navController,
            startDestination = Destinations.LoginScreen.route
        ){
            addLogin(navController)
            addRegister(navController)
            addHome()
        }//fin del navhost
    }//Fin del boxwithconstraint
}//fin de la funcion
@ExperimentalAnimationApi
fun NavGraphBuilder.addLogin(
    navController: NavHostController
){
    composable(
        route = Destinations.LoginScreen.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 1000 },  animationSpec = tween(500)
            )  },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { -1000 }, animationSpec = tween(500)
            )  },
        popEnterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -1000 }, animationSpec = tween(500)
            )  },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 }, animationSpec = tween(500)
            )  }
    ){
        val viewModel = LoginViewModel()
        if( viewModel.state.value.successLogin){
            LaunchedEffect(key1 = Unit){
                navController.navigate(Destinations.HomeScreen.route)
                //mmodificamos el historial de navegacikon para que no pueda regresar
                {
                    popUpTo(Destinations.LoginScreen.route){
                        inclusive = true
                    }
                }
            }
        }else {
            LoginScreen(
                state = viewModel.state.value,
                onLogin = viewModel::login,
                onNavigateToRegister = { navController.navigate(Destinations.RegistrationScreen.route) },
                onDismissDiolog = viewModel::hideErrorDialog
            ) //Agregamos la pantalla a mostrar
        }

    }
}//fin de la funcion navGraphBuilder

@ExperimentalAnimationApi
fun NavGraphBuilder.addRegister(
    navController: NavHostController
){
    composable(
        route = Destinations.RegistrationScreen.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 1000 },  animationSpec = tween(500)
            )  },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { -1000 }, animationSpec = tween(500)
            )  },
        popEnterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -1000 }, animationSpec = tween(500)
            )  },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 }, animationSpec = tween(500)
            )  }
    ){
        val viewModel = RegisterViewModel()
        RegistrationScreen(
            state = viewModel.state.value,
            onRegister = viewModel::register,
            onBack = { navController.popBackStack() },
            onDismissDialog = viewModel::hideErrorDialog
        ) //Agregamos la pantalla a mostrar
    }
}//fin de la funcion navGraphBuilder
@ExperimentalAnimationApi
fun NavGraphBuilder.addHome( ){
    composable(
        route = Destinations.HomeScreen.route,
    ){
        HomeScreen() //Agregamos la pantalla a mostrar
    }
}//fin de la funcion navGraphBuilder
















