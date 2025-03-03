package com.saraebadi.quizcodingcompose.data.repository

import com.saraebadi.quizcodingcompose.data.datasource.ApiDataSource
import com.saraebadi.quizcodingcompose.data.model.QuizDto
import com.saraebadi.quizcodingcompose.domin.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val apiDataSource: ApiDataSource
): Repository {
    override suspend fun getQuiz(): QuizDto {
        return apiDataSource.getQuiz()
    }
}