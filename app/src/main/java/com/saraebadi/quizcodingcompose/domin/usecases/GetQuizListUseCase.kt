package com.saraebadi.quizcodingcompose.domin.usecases

import com.saraebadi.quizcodingcompose.domin.Repository
import com.saraebadi.quizcodingcompose.domin.models.Quiz
import com.saraebadi.quizcodingcompose.domin.models.toQuiz
import javax.inject.Inject


class GetQuizListUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Quiz {
        return repository.getQuizList().toQuiz()
    }
}