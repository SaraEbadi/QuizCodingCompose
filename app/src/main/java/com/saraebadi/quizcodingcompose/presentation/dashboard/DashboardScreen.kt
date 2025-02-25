package com.saraebadi.quizcodingcompose.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saraebadi.quizcodingcompose.R
import com.saraebadi.quizcodingcompose.presentation.quiz.QuizRoute
import com.saraebadi.quizcodingcompose.presentation.util.ObserveAsEffects
import com.saraebadi.quizcodingcompose.ui.theme.DarkBlue
import com.saraebadi.quizcodingcompose.ui.theme.LightBlue
import kotlinx.coroutines.flow.Flow


@Composable
fun DashboardScreen(
    state: DashboardUiState,
    effects: Flow<DashboardEffects>,
    dashboardActions: DashboardActions,
    navigateToQuizScreen: (QuizRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    ObserveAsEffects(effects = effects) { effect ->
        when(effect) {
            is DashboardEffects.GoToQuizScreen -> navigateToQuizScreen(effects.route)
        }
    }
    Column(
        modifier = modifier
            .background(DarkBlue)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.check_24),
            fontSize = 72.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.quiz),
            fontSize = 32.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.high_score),
            fontSize = 16.sp,
            color = Color.White
        )
        Text(
            text = stringResource(R.string.Score, 500),
            fontSize = 16.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            colors =ButtonDefaults.buttonColors(
                containerColor = LightBlue
            ),
            shape = RoundedCornerShape(4.dp),
            content = {
                Text(text = stringResource(R.string.start),
                    fontSize = 14.sp)
            },
            onClick = {

            }
        )

    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    DashboardScreen()
}