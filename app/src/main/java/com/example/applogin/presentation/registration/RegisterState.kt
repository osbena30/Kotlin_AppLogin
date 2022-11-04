package com.example.applogin.presentation.registration

import androidx.annotation.StringRes

data class RegisterState(
    val sucssesRegister : Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessages: Int? = null

)