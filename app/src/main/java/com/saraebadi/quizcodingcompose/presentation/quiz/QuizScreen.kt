package com.saraebadi.quizcodingcompose.presentation.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
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
                AsyncImage(
                    model = state.quiz?.questionImageUrl,
                    contentDescription = "quiz-image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(200.dp)
                )
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
                val isCorrect = state.userAnswerKey == state.quiz?.correctAnswer
                val color = when {
                    state.userAnswerKey == null -> Color.White
                    state.isAnswered && !isCorrect && entry.key == state.quiz?.correctAnswer-> Color.Green
                    state.userAnswerKey != entry.key -> Color.White
                    state.isAnswered && !isCorrect -> Color.Red
                    state.isAnswered && isCorrect -> Color.Green
                    else -> Color.White
                }
                Spacer(Modifier.height(8.dp))
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = color,
                            disabledContainerColor = color),
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