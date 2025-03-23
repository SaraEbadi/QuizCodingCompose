package com.saraebadi.quizcodingcompose.presentation.quiz

sealed interface QuizEffects {
    data object GoToDashboard: QuizEffects
}