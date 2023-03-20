package com.ricdip.tictactoegame.utils

import com.google.common.truth.Truth
import com.ricdip.tictactoegame.model.Board
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UtilsTest {

    @Test
    fun getInitialBoard() {
        val board = Board()

        Truth.assertThat(board).isEqualTo(Utils.getInitialBoard())
    }

    @Test
    fun getInitialBoardHashCode() {
        val board = Board()

        Truth.assertThat(board.getGameBoardHashCode()).isEqualTo(Utils.getInitialBoardHashCode())
    }
}