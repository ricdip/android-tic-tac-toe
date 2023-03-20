package com.ricdip.tictactoegame.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.ricdip.tictactoegame.utils.Game
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameViewModelTest {
    private val gameViewModel = GameViewModel()

    @Test
    fun makeMove() {
        gameViewModel.makeMove(0u, 0u)

        // the white player and the black player (ai) must have played (makeMove executes player move and ai move)
        val currentGameUiState = gameViewModel.uiState.value

        Truth.assertThat(currentGameUiState.board.turn).isEqualTo(Game.WHITE)
        Truth.assertThat(currentGameUiState.board.gameOver).isEqualTo(false)
        Truth.assertThat(currentGameUiState.board.draw).isEqualTo(false)
        Truth.assertThat(currentGameUiState.board.winner).isEqualTo(Game.NONE)

        Truth.assertThat(charRepetitions(currentGameUiState.board.getGameBoard(), Game.WHITE))
            .isEqualTo(1)
        Truth.assertThat(charRepetitions(currentGameUiState.board.getGameBoard(), Game.BLACK))
            .isEqualTo(1)
    }

    private fun charRepetitions(s: String, c: Char): Int {
        var count = 0
        for (e in s) {
            if (e == c) {
                count += 1
            }
        }
        return count
    }
}