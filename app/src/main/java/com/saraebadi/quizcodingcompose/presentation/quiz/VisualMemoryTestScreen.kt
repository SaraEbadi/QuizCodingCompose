package com.saraebadi.quizcodingcompose.presentation.quiz

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun VisualMemoryTest() {
    var level by remember { mutableStateOf(1) }
    var lives by remember { mutableStateOf(3) }
    var gameState by remember { mutableStateOf(GameState.START) }
    var gridSize by remember { mutableStateOf(3) } // Starting with 3x3 grid
    var tilesToRemember by remember { mutableStateOf(emptyList<Pair<Int, Int>>()) }
    var clickedTiles by remember { mutableStateOf(emptySet<Pair<Int, Int>>()) }
    var incorrectClicks by remember { mutableStateOf(emptySet<Pair<Int, Int>>()) }
    var currentDisplayIndex by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }

    val scope = rememberCoroutineScope()

    // Function to start a new level
    fun startNewLevel() {
        clickedTiles = emptySet()
        incorrectClicks = emptySet()
        currentDisplayIndex = -1

        // Adjust grid size based on level
        gridSize = when {
            level < 3 -> 3
            level < 6 -> 4
            level < 9 -> 5
            else -> 6
        }

        // Determine number of tiles to remember (gradually increases with level)
        val numTilesToRemember = level + 2

        // Generate random tiles to remember
        val newTiles = mutableListOf<Pair<Int, Int>>()
        while (newTiles.size < numTilesToRemember) {
            val x = Random.nextInt(gridSize)
            val y = Random.nextInt(gridSize)
            val pair = x to y
            if (pair !in newTiles) {
                newTiles.add(pair)
            }
        }
        tilesToRemember = newTiles

        // Show targets sequentially
        gameState = GameState.SHOWING

        scope.launch {
            // Start the sequence display
            for (i in tilesToRemember.indices) {
                currentDisplayIndex = i
                delay(700) // Show each tile for 700ms
            }

            // After showing all tiles, hide everything and start playing
            currentDisplayIndex = -1
            delay(300) // Short delay for animation
            gameState = GameState.PLAYING
        }
    }

    // Function to handle tile clicks
    fun onTileClick(x: Int, y: Int) {
        if (gameState != GameState.PLAYING) return

        val clickedPosition = x to y

        // Check if this tile was already clicked
        if (clickedPosition in clickedTiles) return

        if (clickedPosition in tilesToRemember) {
            // Correct click
            clickedTiles = clickedTiles + clickedPosition

            // Check if all targets found
            if (clickedTiles.size == tilesToRemember.size) {
                // Level completed
                score += level * 10
                level++
                scope.launch {
                    gameState = GameState.LEVEL_COMPLETE
                    delay(1500)
                    startNewLevel()
                }
            }
        } else {
            // Incorrect click
            incorrectClicks = incorrectClicks + clickedPosition
            lives--

            if (lives <= 0) {
                gameState = GameState.GAME_OVER
            }
        }
    }

    // Function to restart the game
    fun restartGame() {
        level = 1
        lives = 3
        score = 0
        gameState = GameState.START
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Game title
        Text(
            text = "Visual Memory Test",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Status info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatusCard(title = "Level", value = level.toString())
            StatusCard(title = "Lives", value = lives.toString())
            StatusCard(title = "Score", value = score.toString())
        }

        // Game area
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth(0.8f),
            contentAlignment = Alignment.Center
        ) {
            when (gameState) {
                GameState.START -> {
                    Button(
                        onClick = { startNewLevel() },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Start Game")
                    }
                }
                GameState.GAME_OVER -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Game Over!",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                        Text(
                            "Final Score: $score",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Button(
                            onClick = { restartGame() },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("Play Again")
                        }
                    }
                }
                else -> {
                    // Game grid
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (y in 0 until gridSize) {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                for (x in 0 until gridSize) {
                                    val position = x to y
                                    val isCurrentlyShowing =
                                        currentDisplayIndex >= 0 &&
                                                currentDisplayIndex < tilesToRemember.size &&
                                                tilesToRemember[currentDisplayIndex] == position

                                    MemoryTile(
                                        isTarget = position in tilesToRemember,
                                        showTarget = isCurrentlyShowing,
                                        isClicked = position in clickedTiles,
                                        isIncorrect = position in incorrectClicks,
                                        onClick = { onTileClick(x, y) },
                                        modifier = Modifier
                                            .padding(2.dp)
                                            .weight(1f)
                                            .aspectRatio(1f)
                                    )
                                }
                            }
                        }
                    }

                    // Overlay message for level completion
                    AnimatedVisibility(
                        visible = gameState == GameState.LEVEL_COMPLETE,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            color = Color.Black.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    "Level Complete!",
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Instructions
        if (gameState == GameState.SHOWING || gameState == GameState.PLAYING) {
            Text(
                text = if (gameState == GameState.SHOWING) "Remember the sequence" else "Click on the squares you memorized",
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun StatusCard(title: String, value: String) {
    Card(
        modifier = Modifier.padding(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Text(title, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MemoryTile(
    isTarget: Boolean,
    showTarget: Boolean,
    isClicked: Boolean,
    isIncorrect: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val targetAlpha by animateFloatAsState(if (showTarget) 1f else 0f)

    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(4.dp),
        colors = CardColors(
            containerColor = when {
                isIncorrect -> Color.Red.copy(alpha = 0.7f)
                isClicked -> Color.Green.copy(alpha = 0.7f)
                else -> Color.Gray.copy(alpha = 0.2f)
            },
            contentColor = Color.White,
            disabledContainerColor = Color.Gray.copy(alpha = 0.2f),
            disabledContentColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Show blue overlay for the current target in the sequence
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(targetAlpha)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Blue.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(2.dp)
                ) {}
            }
        }
    }
}

enum class GameState {
    START,
    SHOWING,
    PLAYING,
    LEVEL_COMPLETE,
    GAME_OVER
}