package com.saraebadi.quizcodingcompose.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saraebadi.quizcodingcompose.domin.models.Question
import com.saraebadi.quizcodingcompose.domin.usecases.GetQuizListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizListUseCase: GetQuizListUseCase
): ViewModel(), QuizActions {

    private var _uiState = MutableStateFlow(QuizListUiState())
    val uiState = _uiState.asStateFlow()

    val questions  =  MutableStateFlow<List<Question>>(mutableListOf())
    val _questions = questions.onEach {


    }

    fun getQuizList() {
        _uiState.update { state -> state.copy(isLoading = true)}
        viewModelScope.launch {
            try {
                _uiState.update { state -> state.copy(isLoading = false,
                    quiz = getQuizListUseCase().questions.shuffled().toMutableList().first()
                )}
//                questions.update {
//                    getQuizListUseCase().questions.shuffled().toMutableList()
//                }

            } catch (e: Exception) {
                _uiState.update { state -> state.copy(isLoading = false)}
            }
        }
    }

    override fun onNextQuiz() {

    }
}