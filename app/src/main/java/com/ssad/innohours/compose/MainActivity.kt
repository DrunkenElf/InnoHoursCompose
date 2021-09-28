package com.ssad.innohours.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ssad.innohours.compose.ui.theme.InnoHoursComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InnoHoursComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Image(
                        painterResource(id = R.drawable.bg),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxWidth(),
                        alpha = 0.2f,
                    )
                    LoginAndRegistration()
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginAndRegistration(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_screen", builder = {
        composable("login_screen", content = { LoginScreen(navController = navController) })
        composable("register_screen", content = { RegisterScreen(navController = navController) })
    })
}

