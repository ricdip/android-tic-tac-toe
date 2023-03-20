package com.ricdip.tictactoegame.model

import com.google.common.truth.Truth
import com.ricdip.tictactoegame.ni.BoardOutput
import com.ricdip.tictactoegame.utils.Game
import com.ricdip.tictactoegame.utils.Utils

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BoardTest {

    @Test
    fun getTurn() {
        val board1 =
            Board(Utils.getInitialBoard().getGameBoard(), Game.WHITE, false, Game.NONE, false)
        val board2 =
            Board(Utils.getInitialBoard().getGameBoard(), Game.BLACK, false, Game.NONE, false)

        Truth.assertThat(board1.turn).isEqualTo(Game.WHITE)
        Truth.assertThat(board2.turn).isEqualTo(Game.BLACK)
    }

    @Test
    fun getGameOver() {
        val board1 =
            Board(Utils.getInitialBoard().getGameBoard(), Game.WHITE, false, Game.NONE, false)
        val board2 =
            Board(Utils.getInitialBoard().getGameBoard(), Game.WHITE, true, Game.NONE, false)

        Truth.assertThat(board1.gameOver).isEqualTo(false)
        Truth.assertThat(board2.gameOver).isEqualTo(true)
    }

    @Test
    fun getWinner() {
        val board1 =
            Board(Utils.getInitialBoard().getGameBoard(), Game.WHITE, false, Game.WHITE, false)
        val board2 =
            Board(Utils.getInitialBoard().getGameBoard(), Game.WHITE, false, Game.BLACK, false)
        val board3 =
            Board(Utils.getInitialBoard().getGameBoard(), Game.WHITE, false, Game.NONE, false)

        Truth.assertThat(board1.winner).isEqualTo(Game.WHITE)
        Truth.assertThat(board2.winner).isEqualTo(Game.BLACK)
        Truth.assertThat(board3.winner).isEqualTo(Game.NONE)
    }

    @Test
    fun getDraw() {
        val board1 =
            Board(Utils.getInitialBoard().getGameBoard(), Game.WHITE, false, Game.NONE, false)
        val board2 =
            Board(Utils.getInitialBoard().getGameBoard(), Game.WHITE, false, Game.NONE, true)

        Truth.assertThat(board1.draw).isEqualTo(false)
        Truth.assertThat(board2.draw).isEqualTo(true)
    }

    @Test
    fun makeMoveBoardOutput() {
        val board = Board()

        board.makeMove(BoardOutput(0, 0, false, Game.NONE, false))

        Truth.assertThat(board.getGameBoard()).isEqualTo("X........")
    }

    @Test
    fun testToString() {
        val initialBoard = Board()
        val board = Board(".XO.X....", Game.BLACK, false, Game.NONE, false)

        Truth.assertThat(initialBoard.toString()).isEqualTo("........./X/false/./false")
        Truth.assertThat(board.toString()).isEqualTo(".XO.X..../O/false/./false")
    }

    @Test
    fun getGameBoard() {
        val board = Board(".XO.X....", Game.BLACK, false, Game.NONE, false)

        Truth.assertThat(board.getGameBoard()).isEqualTo(".XO.X....")
    }

    @Test
    fun getGameBoardHashCode() {
        val board = Board(".XO.X....", Game.BLACK, false, Game.NONE, false)

        Truth.assertThat(board.getGameBoardHashCode()).isEqualTo(".XO.X....".hashCode())
    }

    @Test
    fun copy() {
        val board = Board(".XO.X....", Game.BLACK, false, Game.NONE, false)

        Truth.assertThat(board.copy()).isEqualTo(board);
    }
}