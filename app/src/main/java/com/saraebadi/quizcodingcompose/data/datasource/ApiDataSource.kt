package com.saraebadi.quizcodingcompose.data.datasource

import com.saraebadi.quizcodingcompose.data.model.QuizDto

interface ApiDataSource {
    suspend fun getQuiz(): QuizDto
}