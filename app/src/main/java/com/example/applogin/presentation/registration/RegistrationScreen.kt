package com.example.applogin.presentation.registration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.applogin.presentation.components.RoundedButton
import com.example.applogin.presentation.components.TransparentTextField
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.example.applogin.presentation.components.EventDialog
import com.example.applogin.presentation.components.SocialMediaButton

@Composable
fun RegistrationScreen(
    state : RegisterState,
    onRegister : (String, String, String, String, String) -> Unit,
    onBack : () -> Unit,
    onDismissDialog : () -> Unit
){
    val nameValue = remember{ mutableStateOf( "")}
    val emailValue = remember{ mutableStateOf( "")}
    val phoneValue = remember{ mutableStateOf( "")}
    val passValue = remember{ mutableStateOf( "")}
    val confirmPassValue = remember{ mutableStateOf( "")}
    var passwordVisibility by remember{ mutableStateOf( false)}
    var confirmPasswordVisibility by remember{ mutableStateOf( false)}
    val focusManager = LocalFocusManager.current
    Box(modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())){
            Row(verticalAlignment = Alignment.CenterVertically){
                IconButton(onClick = {
                    //llamamos onBack
                    onBack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Regresar al Login",
                    tint = MaterialTheme.colors.primary)
                }
                Text( text="Crear una cuenta.", style= MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.primary
                ))
            }//Fin de la fila inicial
            Column( modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                TransparentTextField( //Texto nombre
                    textFieldValue = nameValue,
                    textLabel = "Name",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(  //Texto correo
                    textFieldValue = emailValue,
                    textLabel = "Correo",
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(  //Texto telefono
                    textFieldValue = phoneValue,
                    textLabel = "Telefono",
                    maxChar = 10,
                    keyboardType = KeyboardType.Phone,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(   //Texto clave
                    textFieldValue = passValue,
                    textLabel = "Password",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }){
                            Icon(
                                imageVector = if (passwordVisibility) {
                                    Icons.Default.Visibility
                                } else {
                                    Icons.Default.VisibilityOff
                                },
                                contentDescription = "Toggle"
                            )
                        }
                    }, //fin del trailingIcon
                    visualTransformation = if (passwordVisibility) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )//fin del textField del password
                TransparentTextField(  //Texto  confirmar clave
                    textFieldValue = confirmPassValue,
                    textLabel = "Confirm Password",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            //funcion de confirmar
                            onRegister(
                                nameValue.value,
                                emailValue.value,
                                phoneValue.value,
                                passValue.value,
                                confirmPassValue.value
                            )
                        }
                    ),
                    imeAction = ImeAction.Done,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }){
                            Icon(
                                imageVector = if (passwordVisibility) {
                                    Icons.Default.Visibility
                                } else {
                                    Icons.Default.VisibilityOff
                                },
                                contentDescription = "Toggle"
                            )
                        }
                    }, //fin del trailingIcon
                    visualTransformation = if (passwordVisibility) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )//fin del textField del confirm password

                Spacer(modifier = Modifier.height(16.dp)) // espacio
                RoundedButton(
                    text = "Sign Up",
                    displayProgressBar = state.displayProgressBar,
                    onClick = { onRegister(
                        nameValue.value,
                        emailValue.value,
                        phoneValue.value,
                        passValue.value,
                        confirmPassValue.value
                        )
                     }
                )//Boton de Sing Up
                ClickableText(text = buildAnnotatedString {
                    append("Already have Account")
                    withStyle(
                        style = SpanStyle(
                            color=  MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold
                        )
                    ){
                        append("Log In ")
                    }
                } ,
                    onClick = {
                        onBack()
                    } ) //Already have a Account
            }//Fin de la columna interior
            Spacer(modifier = Modifier.height(16.dp)) // espacio
            Column( verticalArrangement = Arrangement.spacedBy(2.dp)){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray )//Linea divisoria
                    Text( modifier = Modifier.padding(8.dp),
                    text = "OR",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Black)
                    ) // linea OR
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray )//Linea divisoria
                    //Botones adicionales de redes sociales
                }//fin de la fila
                Text( modifier = Modifier.padding(8.dp),
                    text = "Login With",
                    style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary),
                    textAlign = TextAlign.Center
                )
            }//Fin de la columna interna
            Spacer(modifier = Modifier.height(16.dp)) // espacio
            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                //TODO { Creamos un nuevo Componente para los botones.}
                SocialMediaButton(
                    text = "Login With FaceBook",
                    onClick = { /*TODO*/ },
                    socialMediaColor = MaterialTheme.colors.onSurface
                )
                SocialMediaButton(
                    text = "Login With Gmail",
                    onClick = { /*TODO*/ },
                    socialMediaColor = MaterialTheme.colors.primary
                )
            }//fin de la columna contenedora de redes sociales
        }//Fin de la columna exterior
        //colocamos el condicional
        if(state.errorMessages != null){
            EventDialog(errorMessage = state.errorMessages , onDismiss = onDismissDialog)
        }

    }//Fin del Box
}//Fin de la funcion principal

















