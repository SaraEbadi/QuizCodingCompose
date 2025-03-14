package com.saraebadi.quizcodingcompose.data.model

import kotlinx.serialization.Serializable

@Serializable
data class QuizDto(
    val questions: List<QuestionDto>
)

@Serializable
data class QuestionDto(
    val question: String,
    val answers: Map<String, String>,
    val questionImageUrl: String? = null,
    val correctAnswer: String,
    val score: Int
)
