package com.example.unscramble.ui

data class GameUiState(
    val currentScrambleWord: String = "",
    val currentWordCount: Int = 1,
    val isGuessedWrongWord: Boolean = false,
    val score: Int = 0,
    val isGameOver: Boolean = false
)
