package com.saraebadi.quizcodingcompose.presentation.quiz

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saraebadi.quizcodingcompose.R
import com.saraebadi.quizcodingcompose.ui.theme.DarkBlue
import com.saraebadi.quizcodingcompose.ui.theme.QuizCodingComposeTheme

@Composable
fun QuizScreen(
    uiState: QuizListUiState,
    modifier: Modifier = Modifier) {
    val lists = listOf(1,2,3,4)
    Column(modifier = modifier.background(DarkBlue).fillMaxSize()) {
        Spacer(Modifier.height(40.dp))
        Row(modifier = Modifier.fillMaxWidth().background(Color.White),
            horizontalArrangement = Arrangement.Center
            ) {
            Text(
                text = "Frage 2/3",
                fontSize = 14.sp,
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
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "score of this Q", color = Color.Green)
                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "")
                Spacer(Modifier.height(16.dp))
                Text(text = "question")
            }
        }

        LazyColumn {
            items(lists.size, key = { item -> item}) {

            }
        }
    }
}

@Preview
@Composable
private fun QuizScreenPreview() {
    QuizCodingComposeTheme {
        QuizScreen(QuizListUiState())
    }
}