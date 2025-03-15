package com.saraebadi.quizcodingcompose.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saraebadi.quizcodingcompose.domin.models.Question
import com.saraebadi.quizcodingcompose.domin.usecases.GetQuizListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizListUseCase: GetQuizListUseCase
): ViewModel(), QuizActions {

    private val _uiState = MutableStateFlow(QuizListUiState())
    val uiState = _uiState.onStart {
        getQuizList()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        QuizListUiState()
    )

    val questions  =  MutableStateFlow<List<Question>>(mutableListOf())
    val _questions = questions.onEach {


    }

    fun getQuizList() {
        _uiState.update { state -> state.copy(isLoading = true)}
        viewModelScope.launch {
            try {
                val questions = getQuizListUseCase().questions.shuffled()
                _uiState.update { state -> state.copy(isLoading = false,
                    quiz = questions.first(),
                    questions = questions
                )}
//                questions.update {
//                    getQuizListUseCase().questions.shuffled().toMutableList()
//                }

            } catch (e: Exception) {
                _uiState.update { state -> state.copy(isLoading = false)}
            }
        }
    }

    override fun onAnswerClicked(answerKey: String) {
        viewModelScope.launch {
//            _uiState.update { state ->
//                Log.d("AnswerTAG", "checkAnswer: $answerKey")
//                state.copy(isAnswered = true, isCorrect = )
//            }
            _uiState.update { state ->
                state.copy(isAnswered = true)
            }
            _uiState.update { state ->
                state.copy(isCorrect = checkAnswer(answerKey))
            }

            delay(2000)
            Log.d("AnswerTAG", "delay")
            _uiState.update { state ->
                state.copy(
                    quiz = state.questions[state.questionIndex + 1],
                    questionIndex = state.questionIndex + 1,
                    isAnswered = false,
                    isCorrect = null
                )
            }
        }

    }

    private fun checkAnswer(answerKey: String): Boolean{
       return _uiState.value.questions[_uiState.value.questionIndex].correctAnswer == answerKey
    }
}