package com.saraebadi.quizcodingcompose.data.network

import com.saraebadi.quizcodingcompose.data.model.QuizDto
import retrofit2.http.GET

interface ApiService {
    @GET("vg2-quiz/quiz.json")
    suspend fun getQuiz(): QuizDto
}