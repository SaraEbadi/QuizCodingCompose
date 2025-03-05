package com.saraebadi.quizcodingcompose.presentation.quiz

import com.saraebadi.quizcodingcompose.domin.models.Question

data class QuizListUiState(
    val quizList: List<Question> = emptyList(),
    val isLoading: Boolean = false
)
