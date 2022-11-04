package com.example.applogin.presentation.login
import androidx.annotation.StringRes
data class LoginState(
    val email : String = "",
    val pass : String = "",
    val successLogin : Boolean = false,
    val displayProgressBar: Boolean = false,
    //agregamos un recurso de tipo string @StringResoruce que manejra los errores
    @StringRes val errorMessages: Int? = null
)
