package com.saraebadi.quizcodingcompose.domin

import com.saraebadi.quizcodingcompose.data.model.QuizDto

interface Repository {
    suspend fun getQuiz(): QuizDto
}