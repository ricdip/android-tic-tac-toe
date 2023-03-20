package com.ricdip.tictactoegame.ni

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.ricdip.tictactoegame.utils.Game
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NativeGameTest {

    @Test
    fun getBoardStatus() {
        val boardInputInit = BoardInput(".........", Game.WHITE)
        val boardOutputInit = NativeGame.getBoardStatus(boardInputInit)

        val boardInputWinX = BoardInput("X.OOX...X", Game.BLACK)
        val boardOutputWinX = NativeGame.getBoardStatus(boardInputWinX)

        val boardInputWinO = BoardInput("OXXO.XO..", Game.WHITE)
        val boardOutputWinO = NativeGame.getBoardStatus(boardInputWinO)

        val boardInputDraw = BoardInput("XOXXOOOXX", Game.BLACK)
        val boardOutputDraw = NativeGame.getBoardStatus(boardInputDraw)

        Truth.assertThat(boardOutputInit.gameOver).isEqualTo(false)
        Truth.assertThat(boardOutputInit.winner).isEqualTo(Game.NONE)
        Truth.assertThat(boardOutputInit.draw).isEqualTo(false)

        Truth.assertThat(boardOutputWinX.gameOver).isEqualTo(true)
        Truth.assertThat(boardOutputWinX.winner).isEqualTo(Game.WHITE)
        Truth.assertThat(boardOutputWinX.draw).isEqualTo(false)

        Truth.assertThat(boardOutputWinO.gameOver).isEqualTo(true)
        Truth.assertThat(boardOutputWinO.winner).isEqualTo(Game.BLACK)
        Truth.assertThat(boardOutputWinO.draw).isEqualTo(false)

        Truth.assertThat(boardOutputDraw.gameOver).isEqualTo(true)
        Truth.assertThat(boardOutputDraw.winner).isEqualTo(Game.NONE)
        Truth.assertThat(boardOutputDraw.draw).isEqualTo(true)
    }

    @Test
    fun getNextAiMove() {
        val boardInputInit = BoardInput(".........", Game.WHITE)
        val boardOutputInit = NativeGame.getBoardStatus(boardInputInit)

        val boardInputInGame = BoardInput(".X.......", Game.BLACK)
        val boardOutputInGame = NativeGame.getBoardStatus(boardInputInGame)

        val boardInputWinX = BoardInput("X.OOX...X", Game.BLACK)
        val boardOutputWinX = NativeGame.getBoardStatus(boardInputWinX)

        val boardInputWinO = BoardInput("OXXO.XO..", Game.WHITE)
        val boardOutputWinO = NativeGame.getBoardStatus(boardInputWinO)

        val boardInputDraw = BoardInput("XOXXOOOXX", Game.BLACK)
        val boardOutputDraw = NativeGame.getBoardStatus(boardInputDraw)

        Truth.assertThat(boardOutputInit.gameOver).isEqualTo(false)
        Truth.assertThat(boardOutputInit.winner).isEqualTo(Game.NONE)
        Truth.assertThat(boardOutputInit.draw).isEqualTo(false)

        Truth.assertThat(boardOutputInGame.gameOver).isEqualTo(false)
        Truth.assertThat(boardOutputInGame.winner).isEqualTo(Game.NONE)
        Truth.assertThat(boardOutputInGame.draw).isEqualTo(false)

        Truth.assertThat(boardOutputWinX.gameOver).isEqualTo(true)
        Truth.assertThat(boardOutputWinX.winner).isEqualTo(Game.WHITE)
        Truth.assertThat(boardOutputWinX.draw).isEqualTo(false)

        Truth.assertThat(boardOutputWinO.gameOver).isEqualTo(true)
        Truth.assertThat(boardOutputWinO.winner).isEqualTo(Game.BLACK)
        Truth.assertThat(boardOutputWinO.draw).isEqualTo(false)

        Truth.assertThat(boardOutputDraw.gameOver).isEqualTo(true)
        Truth.assertThat(boardOutputDraw.winner).isEqualTo(Game.NONE)
        Truth.assertThat(boardOutputDraw.draw).isEqualTo(true)
    }
}