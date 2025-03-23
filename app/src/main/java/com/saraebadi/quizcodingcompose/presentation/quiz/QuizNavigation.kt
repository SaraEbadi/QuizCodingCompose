package com.saraebadi.quizcodingcompose.presentation.quiz

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object QuizRoute

fun NavGraphBuilder.quizScreen(goToDashboard: () -> Unit) {
    composable<QuizRoute> {
        val viewModel: QuizViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsState()
        QuizScreen(
            state = state,
            actions = viewModel,
            effects = viewModel.effects,
            goToDashboard = goToDashboard
        )
    }
}

fun NavController.navigateToQuizScreen(navOption: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = QuizRoute, builder = navOption)
}