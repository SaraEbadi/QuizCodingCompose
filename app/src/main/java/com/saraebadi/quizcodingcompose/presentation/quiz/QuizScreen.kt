package com.saraebadi.quizcodingcompose.presentation.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.saraebadi.quizcodingcompose.ui.theme.QuizCodingComposeTheme

@Composable
fun QuizScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(Color.Green)) {

    }
}

@Preview
@Composable
private fun QuizScreenPreview() {
    QuizCodingComposeTheme {
        QuizScreen()
    }
}