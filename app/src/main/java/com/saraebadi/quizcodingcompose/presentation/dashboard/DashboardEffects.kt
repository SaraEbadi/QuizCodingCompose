package com.saraebadi.quizcodingcompose.presentation.dashboard

import com.saraebadi.quizcodingcompose.presentation.quiz.QuizRoute


sealed interface DashboardEffects {
    data class GoToQuizScreen(val route: QuizRoute): DashboardEffects
}