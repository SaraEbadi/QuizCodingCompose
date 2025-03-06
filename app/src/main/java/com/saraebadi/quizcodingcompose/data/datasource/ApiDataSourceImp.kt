package com.saraebadi.quizcodingcompose.data.datasource

import com.saraebadi.quizcodingcompose.data.network.ApiService
import com.saraebadi.quizcodingcompose.data.model.QuizDto
import javax.inject.Inject

class ApiDataSourceImp @Inject constructor(
    private val apiService: ApiService
): ApiDataSource {
    override suspend fun getQuiz(): QuizDto {
        return apiService.getQuiz()
    }
}