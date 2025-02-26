package com.saraebadi.quizcodingcompose.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saraebadi.quizcodingcompose.presentation.quiz.QuizRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(): ViewModel(), DashboardActions {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState = _uiState.onStart {

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        DashboardUiState()
    )

    private val _effect = Channel<DashboardEffects>()
    val effects = _effect.receiveAsFlow()

    override fun onStartClick() {
        viewModelScope.launch {
            _effect.send(DashboardEffects.GoToQuizScreen(QuizRoute))
        }
    }
}

interface DashboardActions {
    fun onStartClick()
}