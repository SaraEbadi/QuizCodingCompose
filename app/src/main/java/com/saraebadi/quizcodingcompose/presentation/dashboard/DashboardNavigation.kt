package com.saraebadi.quizcodingcompose.presentation.dashboard

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.saraebadi.quizcodingcompose.presentation.quiz.QuizRoute
import kotlinx.serialization.Serializable

@Serializable
data object DashboardRoute

fun NavGraphBuilder.dashboardScreen(
    navigateToQuizScreen: (QuizRoute) -> Unit,
    ) {
    composable<DashboardRoute> {
        val viewModel: DashboardViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        DashboardScreen(
            state = state,
            effects = viewModel.effects,
            navigateToQuizScreen = navigateToQuizScreen,
            dashboardActions = viewModel
        )
    }
}