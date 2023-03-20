package com.ricdip.tictactoegame.viewmodel

import com.google.common.truth.Truth
import com.ricdip.tictactoegame.model.Board
import com.ricdip.tictactoegame.ui.GameUiState
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GameViewModelTest {
    private val initialBoard = Board()

    @Test
    fun resetGame() {
        val gameViewModel = GameViewModel()

        gameViewModel.resetGame()

        Truth.assertThat(gameViewModel.uiState.value)
            .isEqualTo(
                GameUiState(
                    board = initialBoard,
                    gameBoardHashCode = initialBoard.getGameBoardHashCode()
                )
            )
    }
}