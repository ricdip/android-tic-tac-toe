package com.ricdip.tictactoegame.viewmodel

import androidx.lifecycle.ViewModel
import com.ricdip.tictactoegame.ni.BoardInput
import com.ricdip.tictactoegame.ni.NativeGame
import com.ricdip.tictactoegame.ui.GameUiState
import com.ricdip.tictactoegame.utils.Game
import com.ricdip.tictactoegame.utils.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        GameUiState(
            board = Utils.getInitialBoard(),
            gameBoardHashCode = Utils.getInitialBoardHashCode()
        )
    )
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun resetGame() {
        _uiState.value = GameUiState(
            board = Utils.getInitialBoard(),
            gameBoardHashCode = Utils.getInitialBoardHashCode()
        )
    }

    fun makeMove(row: UInt, col: UInt) {
        if (_uiState.value.board.invoke(row, col) == Game.NONE) {
            _uiState.update { gameState ->
                val board = gameState.board
                board.makeMove(row, col)

                gameState.copy(
                    board = board,
                    gameBoardHashCode = board.getGameBoardHashCode(),
                )
            }

            if (!_uiState.value.board.gameOver) {
                aiMakeMove()
            }
        }
    }

    private fun aiMakeMove() {
        _uiState.update { gameState ->
            val board = gameState.board
            val boardOutput = NativeGame.getNextAiMove(
                BoardInput(
                    boardString = board.getGameBoard(),
                    turn = board.turn
                ), aiDepth = gameState.aiDepth.toInt()
            )

            board.makeMove(boardOutput)

            gameState.copy(
                board = board,
                gameBoardHashCode = board.getGameBoardHashCode(),
            )
        }
    }
}