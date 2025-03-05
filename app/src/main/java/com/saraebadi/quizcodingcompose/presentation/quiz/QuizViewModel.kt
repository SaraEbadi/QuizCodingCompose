package com.saraebadi.quizcodingcompose.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saraebadi.quizcodingcompose.domin.usecases.GetQuizListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizListUseCase: GetQuizListUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow(QuizListUiState())
    val uiState = _uiState.asStateFlow()

    fun getQuizList() {
        _uiState.update { state -> state.copy(isLoading = true)}
        viewModelScope.launch {
            try {
                _uiState.update { state -> state.copy(isLoading = false)}
                _uiState.update { state -> state.copy(quizList = getQuizListUseCase().questions)}
            } catch (e: Exception) {
                _uiState.update { state -> state.copy(isLoading = false)}
            }
        }
    }
}