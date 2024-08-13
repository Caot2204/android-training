package com.example.unscramble.ui.test

import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.getUnscrambledWord
import com.example.unscramble.ui.GameViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.testng.Assert.assertNotEquals

class GameViewModelTest {
    private val gameViewModel = GameViewModel()

    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
        var uiState = gameViewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(uiState.currentScrambleWord)

        gameViewModel.updateUserGuess(correctPlayerWord)
        gameViewModel.checkUserGuess()

        uiState = gameViewModel.uiState.value
        assertFalse(uiState.isGuessedWrongWord)
        assertEquals(SCORE_AFTER_FIRTS_CORRECT_ANSWER, uiState.score)
    }

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        val incorrectWord = "and"
        gameViewModel.updateUserGuess(incorrectWord)
        gameViewModel.checkUserGuess()

        val currentUiState = gameViewModel.uiState.value
        assertEquals(0, currentUiState.score)
        assertTrue(currentUiState.isGuessedWrongWord)
    }

    @Test
    fun gameViewModel_Initialization_LoadFirstWord() {
        val currentUIState = gameViewModel.uiState.value
        val unscrambleWord = getUnscrambledWord(currentUIState.currentScrambleWord)

        assertNotEquals(unscrambleWord, currentUIState.currentScrambleWord)
        assertTrue(currentUIState.score == 0)
        assertTrue(currentUIState.currentWordCount == 1)
        assertFalse(currentUIState.isGuessedWrongWord)
        assertFalse(currentUIState.isGameOver)

    }

    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentUiState = gameViewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentUiState.currentScrambleWord)
        repeat(MAX_NO_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            gameViewModel.updateUserGuess(correctPlayerWord)
            gameViewModel.checkUserGuess()
            currentUiState = gameViewModel.uiState.value
            correctPlayerWord = getUnscrambledWord(currentUiState.currentScrambleWord)
            assertEquals(expectedScore, currentUiState.score)
        }
        assertEquals(MAX_NO_OF_WORDS, currentUiState.currentWordCount)
        assertTrue(currentUiState.isGameOver)

    }

    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        var currentGameUiState = gameViewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambleWord)
        gameViewModel.updateUserGuess(correctPlayerWord)
        gameViewModel.checkUserGuess()

        currentGameUiState = gameViewModel.uiState.value
        val lastWordCount = currentGameUiState.currentWordCount
        gameViewModel.skipWord()
        currentGameUiState = gameViewModel.uiState.value
        // Assert that score remains unchanged after word is skipped.
        assertEquals(SCORE_AFTER_FIRTS_CORRECT_ANSWER, currentGameUiState.score)
        // Assert that word count is increased by 1 after word is skipped.
        assertEquals(lastWordCount + 1, currentGameUiState.currentWordCount)
    }

    companion object {
        private const val SCORE_AFTER_FIRTS_CORRECT_ANSWER = SCORE_INCREASE
    }

}

