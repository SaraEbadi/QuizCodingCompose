package com.saraebadi.quizcodingcompose.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saraebadi.quizcodingcompose.domin.models.Question
import com.saraebadi.quizcodingcompose.domin.usecases.GetQuizListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    override fun onAnswerClicked() {
        _uiState.update { state -> state.copy(isAnswered = true) }
        viewModelScope.launch {
            delay(2000)
            _uiState.update { state ->
                state.copy(
                    quiz = state.questions[state.questionIndex + 1],
                    questionIndex = state.questionIndex + 1,
                    isAnswered = false
                )
            }
        }

    }
}

//flow
//fun main(): Unit = runBlocking {
//    val flow = flow {
//        repeat(5){
//            emit(it)
//            delay(500)
//        }
//    }
//    flow.flatMapConcat {
//        delay(1000)
//        println(it)
//        flowOf(4,5,6)
//    }.collectLatest {
//        println("after collect $it")
//    }
//}


//fun main() = runBlocking {
//    val searchFlow = MutableStateFlow("")
//
//
//    launch {
//        searchFlow
////            .debounce(30) // Simulate user typing delay
//            .filter { query ->
//                if (query.isEmpty()) {
//                    println("Query is empty, ignoring...")
//                    return@filter false
//                } else {
//                    return@filter true
//                }
//            }
//            .distinctUntilChanged() // Ignore repeated queries
//            .flatMapConcat { query ->
//                dataFromNetwork(query)
//                    .catch {
//                        println("Error fetching data: ${it.message}")
//                        emitAll(flowOf("")) // Emit empty result on error
//                    }
//            }
//            .flowOn(Dispatchers.Default) // Run in background
//            .collect { result ->
//                println("Result: $result") // Print to console
//            }
//    }
//
//
//    launch {
//        delay(20)
//        searchFlow.value = "Hello"
//
//        delay(50)
//        searchFlow.value = "Hel"
//
//        delay(20)
//        searchFlow.value = "Hello"
//
//        delay(40)
//        searchFlow.value = "s"
////
////        delay(60)  //
////        searchFlow.value = "Android"
//    }
//
//    delay(50000)
//}
//
//// Simulating a network request
//private fun dataFromNetwork(query: String): Flow<String> {
//    return flow {
//        delay(2000) // Simulated network delay
//        println("Long-running operation completed for: $query")
//        emit(query) // Returning the query as result
//    }
//}