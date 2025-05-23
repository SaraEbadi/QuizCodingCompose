package com.saraebadi.quizcodingcompose.presentation.quiz

import com.saraebadi.quizcodingcompose.domin.models.Question

data class QuizListUiState(
    val questions: List<Question> = emptyList(),
    val quiz: Question ?= null,
    val questionIndex: Int = 0,
    val isLoading: Boolean = false,
    val isAnswered: Boolean = false,
    val isCorrect: Boolean ?= null,
    val userAnswerKey: String?=  null,
    val isGameOver: Boolean = false,
    val userScore: Int = 0
)
