package com.example.applogin.presentation.login
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.applogin.R
import com.example.applogin.presentation.components.EventDialog
import com.example.applogin.presentation.components.RoundedButton
import com.example.applogin.presentation.components.TransparentTextField
@Composable
fun LoginScreen(
    state:LoginState,
    onLogin : ( String , String ) -> Unit,
    onNavigateToRegister : ( ) -> Unit,
    onDismissDiolog : ( ) -> Unit
){
    val emailValue = rememberSaveable { mutableStateOf( "")}
    val passwordValue = rememberSaveable { mutableStateOf( "")}
    var passwordVisibility by remember{ mutableStateOf( false)}
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ){
        Image(
            painter = painterResource(id = R.drawable.login2),
            contentDescription = "Imagen del logo",
            contentScale = ContentScale.Inside
            )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            ConstraintLayout {
            //creamos las referencias de cada elementos del constraint
                val (surface , fab) = createRefs()
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .constrainAs(surface) {
                        //alinea el control al final del surface
                        bottom.linkTo(parent.bottom)
                    },
                color = Color.White,
                shape = RoundedCornerShape(
                    topStartPercent = 8,
                    topEndPercent = 8
                )
            ){
                //boton de input y de password
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Bienvenido",
                        style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(text = "Accede a tu cuenta",
                        style = MaterialTheme.typography.h5.copy(
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    //columna que contendra los textfield
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                                TransparentTextField(
                                    //colocamos el valor de la variable muteable
                                    textFieldValue = emailValue,
                                    //texto que mostrara el c ontrol
                                    textLabel = "eMail",
                                    //tipo de teclado === email
                                    keyboardType = KeyboardType.Email,
                                    //accion del teclado TODO
                                    keyboardActions = KeyboardActions(
                                        onNext = {
                                            focusManager.moveFocus(focusDirection = FocusDirection.Down)
                                        }
                                    ),
                                    //foco a seguir despues de digitar
                                    imeAction = ImeAction.Next
                                )
                            TransparentTextField( //textfield del password
                                //colocamos el valor de la variable muteable
                                textFieldValue = passwordValue,
                                //texto que mostrara el control
                                textLabel = "Password",
                                //tipo de teclado === password
                                keyboardType = KeyboardType.Password,
                                //accion del teclado TODO
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                        //cargamos los datos a pasar cuando se de click en el icono
                                        onLogin( emailValue.value, passwordValue.value)
                                    }
                                ),
                                //terminacion del foco Done
                                imeAction = ImeAction.Done,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            //cambia el valor de la visual de la clave
                                            passwordVisibility = !passwordVisibility
                                        }//fin del onclick
                                    )
                                    {
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
                            )// fin del TextField de la contraseña
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "Forgot Password",
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.End
                            )
                        }//fin columna interna
                    //columna para los botones
                    Column (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy( 8.dp)
                            ) {
                        RoundedButton(
                            text = "Login",
                            displayProgressBar = state.displayProgressBar,
                            onClick = {
                                onLogin( emailValue.value, passwordValue.value)
                            }
                        )// fin del Button
                        ClickableText(
                            text = buildAnnotatedString {
                                append( "No tiene una cuenta activa? ")
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colors.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                ){
                                    append( "Sing Up")
                                }
                            } // fin del texto
                        ){
                            onNavigateToRegister()
                        } // fin del clickableText
                    } // columna para botones adicionales
                }//fin de la columna central
            }//fin del surface
            //Agregamos el floatingActionButton
            FloatingActionButton(
                modifier = Modifier
                    .size(72.dp)
                    .constrainAs(fab) {
                        //alineamos el control en la parte superior del surface
                        top.linkTo(surface.top, margin = (-200).dp)
                        //alineamos el ontro control en la parte inferior
                        end.linkTo(surface.end, margin = 36.dp)
                    },
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {     onNavigateToRegister()  })
                {
                    Icon(
                        modifier = Modifier.size(72.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Boton de avanzar",
                        tint = Color.White
                    )
                }//fin del floatingButton
            } // fin del constraintLayout
        }//Fin del segubdo box
        //validacion de los mensajes de error
        if( state.errorMessages != null){
            //compose creado e importado
            EventDialog(
                errorMessage = state.errorMessages,
                onDismiss = onDismissDiolog
            )
        }
    }//Fin del primer box
}//fin de la pantalla