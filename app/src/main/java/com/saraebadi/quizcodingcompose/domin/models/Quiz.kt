package com.saraebadi.quizcodingcompose.domin.models

import com.saraebadi.quizcodingcompose.data.model.QuestionDto
import com.saraebadi.quizcodingcompose.data.model.QuizDto

data class Quiz(
    val questions: List<Question>
)

data class Question(
    val question: String,
    val answer: Map<String, String>,
    val questionImageUrl: String? = null,
    val correctAnswer: String,
    val score: Int
)

fun QuizDto.toQuiz() : Quiz {
    return Quiz(
        questions = questions.map { it.toQuestion() }
    )
}

fun QuestionDto.toQuestion(): Question {
    return Question(
        question = question,
        answer = answer,
        questionImageUrl = questionImageUrl,
        correctAnswer = correctAnswer,
        score = score
    )
}