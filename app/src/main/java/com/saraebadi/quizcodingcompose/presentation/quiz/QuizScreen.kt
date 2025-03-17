package com.saraebadi.quizcodingcompose.presentation.quiz

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saraebadi.quizcodingcompose.R
import com.saraebadi.quizcodingcompose.ui.theme.DarkBlue
import com.saraebadi.quizcodingcompose.ui.theme.Gray100
import com.saraebadi.quizcodingcompose.ui.theme.QuizCodingComposeTheme

@Composable
fun QuizScreen(
    state: QuizListUiState,
    actions: QuizActions,
    modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .background(DarkBlue)
        .fillMaxSize()) {
        Spacer(Modifier.height(40.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Frage 2/3",
                fontSize = 16.sp,
                color = DarkBlue,
            )
        }
        Spacer(Modifier.height(40.dp))
        Card(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${state.quiz?.score.toString()} Punkte", color = Color.Green)
                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "")
                Spacer(Modifier.height(16.dp))
                Text(
                    text = state.quiz?.question ?: "",
                    color = Gray100,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            itemsIndexed(items = state.quiz?.answers?.entries?.toList() ?: emptyList(), key = null) { index, entry ->
                val isCorrect = state.quiz?.correctAnswer?.contains(entry.key)
                val selectedAnswer = state.userAnswerKey?.let {
                    it == entry.key && state.userAnswerKey == state.quiz?.correctAnswer
                }
                Spacer(Modifier.height(8.dp))
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(8.dp),
                        colors = selectedAnswer?.let {
                            Log.d("AnswerTAG", "cScreen Color${state.isCorrect}")
                            if (it) {
                                ButtonDefaults.buttonColors(
                                    containerColor = Color.Green,
                                    disabledContainerColor = Color.Green)
                            } else {
                                ButtonDefaults.buttonColors(
                                    containerColor = Color.Red,
                                    disabledContainerColor = Color.Red)
                            }
                        }?: ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            disabledContainerColor = Color(0xFFEAF2FF)),
                        onClick = { actions.onAnswerClicked(entry) },
                        enabled = !state.isAnswered
                    ) {
                        Text(text = entry.value, color = Gray100, fontWeight = FontWeight.Bold)
                    }

            }
        }
    }
}

@Preview
@Composable
private fun QuizScreenPreview() {
    QuizCodingComposeTheme {
        QuizScreen(
            state = QuizListUiState(),
            actions = quizActionsPreview)
    }
}

interface QuizActions {
    fun onAnswerClicked(answerKey: Map.Entry<String, String>)
}

val quizActionsPreview = object : QuizActions {
    override fun onAnswerClicked(answerKey: Map.Entry<String, String>) {}

}