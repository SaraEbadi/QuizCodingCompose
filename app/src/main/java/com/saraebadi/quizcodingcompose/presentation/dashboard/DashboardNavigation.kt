package com.saraebadi.quizcodingcompose.presentation.dashboard

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object DashboardRoute

fun NavGraphBuilder.dashboardScreen(onStartClick: () -> Unit) {
    composable<DashboardRoute> {
        val viewModel: DashboardViewModel = hiltViewModel()

        DashboardScreen(

        )
    }
}