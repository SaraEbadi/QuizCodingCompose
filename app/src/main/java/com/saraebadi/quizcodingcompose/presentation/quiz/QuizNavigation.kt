package com.saraebadi.quizcodingcompose.presentation.quiz

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object QuizRoute

fun NavGraphBuilder.quizScreen() {
    val viewModel: QuizViewModel = hiltViewModel()
    composable<QuizRoute> {
        QuizScreen()
    }
}

fun NavController.navigateToQuizScreen(navOption: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = QuizRoute, builder = navOption)
}