package com.ssad.innohours.compose

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ssad.innohours.compose.ui.theme.FigBlue

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val email = remember { mutableStateOf(TextFieldValue()) }
    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = FigBlue)) {
                append("Login")
            }
        }, fontSize = 30.sp)
        Spacer(Modifier.size(16.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                if (emailErrorState.value) {
                    emailErrorState.value = false
                }
                email.value = it
            },
            isError = emailErrorState.value,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Email")
            },
            shape = Shapes().medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = FigBlue,
                unfocusedBorderColor = FigBlue,
                unfocusedLabelColor = FigBlue
            )
        )
        if (emailErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        val passwordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                if (passwordErrorState.value) {
                    passwordErrorState.value = false
                }
                password.value = it
            },
            isError = passwordErrorState.value,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Password")
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = if (passwordVisibility.value) Icons.Default.Lock else Icons.Default.List,
                        contentDescription = "visibility",
                        tint = FigBlue
                    )
                }
            },
            shape = Shapes().medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = FigBlue,
                unfocusedBorderColor = FigBlue,
                unfocusedLabelColor = FigBlue
            ),
            visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
        )
        if (passwordErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = {
                /*navController.navigate("register_screen") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }*/
            }) {
                Text(text = "Forgot Password?", color = FigBlue)
            }
            Spacer(Modifier.size(48.dp))
            TextButton(
                onClick = {
                    when {
                        email.value.text.isEmpty() -> {
                            emailErrorState.value = true
                        }
                        password.value.text.isEmpty() -> {
                            passwordErrorState.value = true
                        }
                        else -> {
                            passwordErrorState.value = false
                            emailErrorState.value = false
                            Toast.makeText(
                                context,
                                "Logged in successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                },
                content = {
                    Text(text = "Login", color = FigBlue)
                },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                border = BorderStroke(Dp(1.0f), FigBlue)
            )
        }
        Spacer(Modifier.size(32.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(8.dp, 8.dp, 32.dp, 8.dp), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = {
                navController.navigate("register_screen") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }) {
                Text(text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = FigBlue)){
                        append("New here?\n")
                    }
                    withStyle(style = SpanStyle(color = FigBlue, fontWeight = FontWeight.W700)){
                        append("Register")
                    }
                })
            }
        }

    }
}