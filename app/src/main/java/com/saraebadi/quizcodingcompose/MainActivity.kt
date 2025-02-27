package com.saraebadi.quizcodingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.saraebadi.quizcodingcompose.presentation.dashboard.DashboardRoute
import com.saraebadi.quizcodingcompose.presentation.dashboard.dashboardScreen
import com.saraebadi.quizcodingcompose.presentation.quiz.navigateToQuizScreen
import com.saraebadi.quizcodingcompose.presentation.quiz.quizScreen
import com.saraebadi.quizcodingcompose.ui.theme.QuizCodingComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizCodingApp()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
        }
    }


}

@Composable
fun QuizCodingApp() {
    QuizCodingComposeTheme {
        val navController = rememberNavController()

        NavHost(
            navController,
            startDestination = DashboardRoute,
        ) {
            dashboardScreen(navigateToQuizScreen = {
                navController.navigateToQuizScreen()
            })

            quizScreen(

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizCodingComposeTheme {

    }
}