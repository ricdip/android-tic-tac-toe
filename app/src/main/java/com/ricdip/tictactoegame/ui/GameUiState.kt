package com.ricdip.tictactoegame.ui

import com.ricdip.tictactoegame.model.Board

data class GameUiState(
    val board: Board,
    val gameBoardHashCode: Int,
    val aiDepth: UInt = 9u
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameUiState

        if (board != other.board) return false
        if (gameBoardHashCode != other.gameBoardHashCode) return false
        if (aiDepth != other.aiDepth) return false

        return true
    }

    override fun hashCode(): Int {
        var result = board.hashCode()
        result = 31 * result + gameBoardHashCode
        result = 31 * result + aiDepth.hashCode()
        return result
    }
}