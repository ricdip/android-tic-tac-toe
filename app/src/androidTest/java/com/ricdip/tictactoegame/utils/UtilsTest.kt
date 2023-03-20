package com.ricdip.tictactoegame.utils


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth
import com.ricdip.tictactoegame.R
import com.ricdip.tictactoegame.model.Board
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilsTest {

    @Test
    fun getGameStatusFromBoard() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val boardInit = Board(".........", Game.WHITE, false, Game.NONE, false)
        val boardInGame = Board(".X.......", Game.BLACK, false, Game.NONE, false)
        val boardWinX = Board("X.OOX...X", Game.BLACK, true, Game.WHITE, false)
        val boardWinO = Board("OXXO.XO..", Game.WHITE, true, Game.BLACK, false)
        val boardDraw = Board("XOXXOOOXX", Game.BLACK, true, Game.NONE, true)

        Truth.assertThat(Utils.getGameStatusFromBoard(appContext, boardInit))
            .isEqualTo(appContext.getString(R.string.game_turn, Game.WHITE))
        Truth.assertThat(Utils.getGameStatusFromBoard(appContext, boardInGame))
            .isEqualTo(appContext.getString(R.string.game_turn, Game.BLACK))
        Truth.assertThat(Utils.getGameStatusFromBoard(appContext, boardWinX))
            .isEqualTo(appContext.getString(R.string.game_over_win, Game.WHITE))
        Truth.assertThat(Utils.getGameStatusFromBoard(appContext, boardWinO))
            .isEqualTo(appContext.getString(R.string.game_over_win, Game.BLACK))
        Truth.assertThat(Utils.getGameStatusFromBoard(appContext, boardDraw))
            .isEqualTo(appContext.getString(R.string.game_over_draw))
    }
}