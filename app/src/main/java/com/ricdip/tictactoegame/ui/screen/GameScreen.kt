package com.ricdip.tictactoegame.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ricdip.tictactoegame.R
import com.ricdip.tictactoegame.ui.theme.TicTacToeGameTheme
import com.ricdip.tictactoegame.utils.Game
import com.ricdip.tictactoegame.utils.Utils
import com.ricdip.tictactoegame.viewmodel.GameViewModel

@Composable
fun GameScreen(gameViewModel: GameViewModel = GameViewModel()) {
    val boardStateUi by gameViewModel.uiState.collectAsState()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (text, board, button) = createRefs()

        Text(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top)
                bottom.linkTo(board.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = Utils.getGameStatusFromBoard(LocalContext.current, boardStateUi.board),
            fontSize = 25.sp
        )

        GameBoard(modifier = Modifier
            .constrainAs(board) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .padding(10.dp), gameViewModel = gameViewModel)

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .constrainAs(button) {
                top.linkTo(board.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, onClick = { gameViewModel.resetGame() }) {
            Text(text = stringResource(id = R.string.game_new), fontSize = 20.sp)
        }
    }
}

@Composable
fun GameBoard(modifier: Modifier, gameViewModel: GameViewModel) {
    val boardStateUi by gameViewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        for (i in 0..2) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                for (j in 0..2) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        GameTile(tile = boardStateUi.board(i.toUInt(), j.toUInt()), onClick = {
                            if (!boardStateUi.board.gameOver) {
                                gameViewModel.makeMove(
                                    i.toUInt(),
                                    j.toUInt()
                                )
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun GameTile(tile: Char, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
            .clickable(onClick = onClick)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(color = Color.Gray, style = Stroke(width = 8f))
        }

        if (tile == Game.WHITE) {
            Canvas(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                val w = size.width
                val h = size.height
                drawLine(
                    strokeWidth = 8f,
                    color = Color.Red,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = w, y = h)
                )
                drawLine(
                    strokeWidth = 8f,
                    color = Color.Red,
                    start = Offset(x = w, y = 0f),
                    end = Offset(x = 0f, y = h)
                )
            }
        } else if (tile == Game.BLACK) {
            Canvas(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                drawCircle(color = Color.Red, style = Stroke(width = 8f))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GamePreview() {
    TicTacToeGameTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            GameScreen()
        }
    }
}