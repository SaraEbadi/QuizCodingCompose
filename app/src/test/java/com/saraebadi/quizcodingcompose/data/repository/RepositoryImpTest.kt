package com.saraebadi.quizcodingcompose.data.repository

import com.saraebadi.quizcodingcompose.data.datasource.ApiDataSource
import com.saraebadi.quizcodingcompose.data.model.QuestionDto
import com.saraebadi.quizcodingcompose.data.model.QuizDto
import com.saraebadi.quizcodingcompose.data.network.ApiService
import org.junit.Test
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.junit.Assert.*

class RepositoryImpTest {

    private val apiDataSource: ApiDataSource = mock()
    val repository = RepositoryImp(apiDataSource)
//
//    @Test
//    fun `Successful quiz retrieval`() = runTest {
//        `when`(apiDataSource.getQuiz())
//            .thenReturn(getQuizDto)
//        // Test if the `getQuizList` function successfully retrieves a list of quizzes
//        // from the `ApiDataSource` and returns a `QuizDto`.
//        // TODO implement test
//    }

    @Test
    fun `Network error during quiz retrieval`() {
        // Test if the `getQuizList` function handles network errors, such as 
        // connection timeouts or server errors, when fetching data from `ApiDataSource`.
        // TODO implement test
    }

    @Test
    fun `API returning empty data`() {
        // Test if the `getQuizList` function correctly handles a scenario where the 
        // `ApiDataSource` returns an empty response, such as an empty quiz list.
        // TODO implement test
    }

    @Test
    fun `API returning null data`() {
        // Test if the `getQuizList` function correctly handles a scenario where the 
        // `ApiDataSource` returns a null response, making sure to handle the potential null pointer exception
        // TODO implement test
    }

    @Test
    fun `Data parsing error`() {
        // Test if the `getQuizList` function handles a scenario where the data 
        // returned by the `ApiDataSource` is in an invalid format and cannot be 
        // parsed into a `QuizDto`.
        // TODO implement test
    }

    @Test
    fun `API returning unexpected data format`() {
        // Test if the `getQuizList` function can handle situations where the 
        // `ApiDataSource` returns a response in an unexpected format (e.g., a string 
        // instead of JSON) that doesn't match the expected `QuizDto` structure.
        // TODO implement test
    }

    @Test
    fun `API service unavailable`() {
        // Test if the `getQuizList` function can gracefully handle scenarios where the 
        // API service is completely unavailable (e.g., the server is down).
        // TODO implement test
    }

    @Test
    fun `Cancellation of the coroutine`() {
        // Test if the `getQuizList` function properly handles cancellation of the 
        // coroutine, ensuring that resources are released and no leaks occur when 
        // the operation is aborted.
        // TODO implement test
    }

    @Test
    fun `Concurrent calls to getQuizList`() {
        // Test if the `getQuizList` function can properly handle multiple calls at the 
        // same time. No exceptions should be thrown, and it should perform normally
        // TODO implement test
    }

    @Test
    fun `Checking for correct data transformation`() {
        // Test to ensure that the ApiDataSource.getQuiz() is correctly creating or 
        // mapping the data to QuizDto.
        // TODO implement test
    }
}

fun getQuizDto() : QuizDto = QuizDto(
    questions = listOf(
        QuestionDto(
            question = "What is this place?",
            answers = mapOf("0" to "Paris", "1" to "Germany", "3" to "Denmark"),
            questionImageUrl = null,
            correctAnswer = "Paris",
            score = 200
        )
    )
)

@RunWith(JUnit4::class)
class HelloTest {

    @Test
    fun sayHello(){
        assertEquals("Hello, world!", hello())
    }

    @Test
    fun sayHelloToSomeOne() {
        assertEquals("Hello, Fred!", hello("Fred"))
    }
}

fun hello(name: String? = null): String{
    if (name == null) {
        return "Hello, world!"
    }
    return "Hello, $name!"
}

