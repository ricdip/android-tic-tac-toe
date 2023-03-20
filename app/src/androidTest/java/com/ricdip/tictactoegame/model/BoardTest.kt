package com.ricdip.tictactoegame.model

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.ricdip.tictactoegame.utils.Game
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BoardTest {

    @Test
    fun makeMove() {
        val board1 = Board()
        board1.makeMove(0u, 0u)
        val board2 = Board()
        board2.makeMove(0u, 1u)
        val board3 = Board()
        board3.makeMove(0u, 2u)
        val board4 = Board()
        board4.makeMove(1u, 0u)
        val board5 = Board()
        board5.makeMove(1u, 1u)
        val board6 = Board()
        board6.makeMove(1u, 2u)
        val board7 = Board()
        board7.makeMove(2u, 0u)
        val board8 = Board()
        board8.makeMove(2u, 1u)
        val board9 = Board()
        board9.makeMove(2u, 2u)

        Truth.assertThat(board1.getGameBoard()).isEqualTo("X........")
        Truth.assertThat(board1.turn).isEqualTo(Game.BLACK)

        Truth.assertThat(board2.getGameBoard()).isEqualTo(".X.......")
        Truth.assertThat(board3.getGameBoard()).isEqualTo("..X......")
        Truth.assertThat(board4.getGameBoard()).isEqualTo("...X.....")
        Truth.assertThat(board5.getGameBoard()).isEqualTo("....X....")
        Truth.assertThat(board6.getGameBoard()).isEqualTo(".....X...")
        Truth.assertThat(board7.getGameBoard()).isEqualTo("......X..")
        Truth.assertThat(board8.getGameBoard()).isEqualTo(".......X.")
        Truth.assertThat(board9.getGameBoard()).isEqualTo("........X")
    }

    @Test
    fun testInvoke() {
        val board1 = Board()
        board1.makeMove(0u, 0u)
        val board2 = Board()
        board2.makeMove(0u, 1u)
        val board3 = Board()
        board3.makeMove(0u, 2u)
        val board4 = Board()
        board4.makeMove(1u, 0u)
        val board5 = Board()
        board5.makeMove(1u, 1u)
        val board6 = Board()
        board6.makeMove(1u, 2u)
        val board7 = Board()
        board7.makeMove(2u, 0u)
        val board8 = Board()
        board8.makeMove(2u, 1u)
        val board9 = Board()
        board9.makeMove(2u, 2u)

        Truth.assertThat(board1(0u, 0u)).isEqualTo(Game.WHITE)
        Truth.assertThat(board2(0u, 1u)).isEqualTo(Game.WHITE)
        Truth.assertThat(board3(0u, 2u)).isEqualTo(Game.WHITE)
        Truth.assertThat(board4(1u, 0u)).isEqualTo(Game.WHITE)
        Truth.assertThat(board5(1u, 1u)).isEqualTo(Game.WHITE)
        Truth.assertThat(board6(1u, 2u)).isEqualTo(Game.WHITE)
        Truth.assertThat(board7(2u, 0u)).isEqualTo(Game.WHITE)
        Truth.assertThat(board8(2u, 1u)).isEqualTo(Game.WHITE)
        Truth.assertThat(board9(2u, 2u)).isEqualTo(Game.WHITE)
    }
}