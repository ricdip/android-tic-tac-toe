package com.ricdip.tictactoegame.utils

import android.content.Context
import com.ricdip.tictactoegame.R
import com.ricdip.tictactoegame.model.Board

object Utils {
    private val initialBoard = Board()

    fun getGameStatusFromBoard(context: Context, board: Board): String {
        return if (!board.gameOver) {
            if (board.turn == Game.WHITE) {
                context.getString(R.string.game_turn, Game.WHITE)
            } else {
                context.getString(R.string.game_turn, Game.BLACK)
            }
        } else {
            if (board.draw) {
                context.getString(R.string.game_over_draw)
            } else {
                context.getString(R.string.game_over_win, board.winner)
            }
        }
    }

    fun getInitialBoard(): Board {
        return initialBoard.copy()
    }

    fun getInitialBoardHashCode(): Int {
        return initialBoard.getGameBoardHashCode()
    }
}