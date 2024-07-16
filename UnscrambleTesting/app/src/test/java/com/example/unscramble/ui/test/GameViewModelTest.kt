package com.example.unscramble.ui.test

import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.getUnscrambledWord
import com.example.unscramble.ui.GameViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GameViewModelTest {
	
	companion object {
		private const val SCORE_AFTER_FIRST_ANSWER = SCORE_INCREASE
	}
	private val gameViewModel = GameViewModel()
	
	@Test
	fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
		var gameUiState = gameViewModel.uiState.value
		val correctPlayerWord = getUnscrambledWord(gameUiState.currentScrambledWord)
		gameViewModel.updateUserGuess(correctPlayerWord)
		gameViewModel.checkUserGuess()
		
		gameUiState = gameViewModel.uiState.value
		
		assertFalse(gameUiState.isGuessedWordWrong)
		assertEquals(SCORE_AFTER_FIRST_ANSWER, gameUiState.score)
	}
	
	@Test
	fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
		val incorrectPlayerWord = "and"
		gameViewModel.updateUserGuess(incorrectPlayerWord)
		gameViewModel.checkUserGuess()
		
		val updatedGameUiState = gameViewModel.uiState.value
		
		assertTrue(updatedGameUiState.isGuessedWordWrong)
		assertEquals(0, updatedGameUiState.score)
	}
	
	@Test
	fun gameViewModel_Initialization_FirstWordLoaded() {
		val gameUiState = gameViewModel.uiState.value
		val unscrabledWord = getUnscrambledWord(gameUiState.currentScrambledWord)
		
		assertNotEquals(unscrabledWord, gameUiState.currentScrambledWord)
		assertEquals(1, gameUiState.currentWordCount)
		assertEquals(0, gameUiState.score)
		assertFalse(gameUiState.isGameOver)
		assertFalse(gameUiState.isGuessedWordWrong)
	}
	
	@Test
	fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
		var gameUiState = gameViewModel.uiState.value
		var expectedScore = 0
		var unscrabledWord = getUnscrambledWord(gameUiState.currentScrambledWord)
		
		repeat(MAX_NO_OF_WORDS) {
			gameViewModel.updateUserGuess(unscrabledWord)
			gameViewModel.checkUserGuess()
			
			gameUiState = gameViewModel.uiState.value
			expectedScore += SCORE_INCREASE
			unscrabledWord = getUnscrambledWord(gameUiState.currentScrambledWord)
			
			assertEquals(expectedScore, gameUiState.score)
		}
		
		assertTrue(gameUiState.isGameOver)
		assertEquals(MAX_NO_OF_WORDS, gameUiState.currentWordCount)
	}
	
	@Test
	fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
		var currentGameUiState = gameViewModel.uiState.value
		val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
		gameViewModel.updateUserGuess(correctPlayerWord)
		gameViewModel.checkUserGuess()
		
		currentGameUiState = gameViewModel.uiState.value
		val lastWordCount = currentGameUiState.currentWordCount
		gameViewModel.skipWord()
		currentGameUiState = gameViewModel.uiState.value
		// Assert that score remains unchanged after word is skipped.
		assertEquals(SCORE_AFTER_FIRST_ANSWER, currentGameUiState.score)
		// Assert that word count is increased by 1 after word is skipped.
		assertEquals(lastWordCount + 1, currentGameUiState.currentWordCount)
	}
}