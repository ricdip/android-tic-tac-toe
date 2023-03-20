package com.ricdip.tictactoegame.model

import com.ricdip.tictactoegame.ni.BoardInput
import com.ricdip.tictactoegame.ni.BoardOutput
import com.ricdip.tictactoegame.ni.NativeGame
import com.ricdip.tictactoegame.utils.Game

class Board {
    private var _gameBoard: CharArray = ".".repeat(Game.MAX_ROWS * Game.MAX_COLS).toCharArray()
    var turn: Char = Game.WHITE
        private set
    var gameOver: Boolean = false
        private set
    var winner: Char = Game.NONE
        private set
    var draw: Boolean = false
        private set

    constructor()

    constructor(boardString: String, turn: Char, gameOver: Boolean, winner: Char, draw: Boolean) {
        _gameBoard = boardString.toCharArray()
        this.turn = turn
        this.gameOver = gameOver
        this.winner = winner
        this.draw = draw
    }

    private constructor(board: Board) {
        _gameBoard = board._gameBoard.clone()
        turn = board.turn
        gameOver = board.gameOver
        winner = board.winner
        draw = board.draw
    }

    fun makeMove(row: UInt, col: UInt) {
        _gameBoard[loc(row, col)] = turn

        val currentStatus = NativeGame.getBoardStatus(
            BoardInput(
                boardString = getGameBoard(),
                turn = turn
            )
        )

        gameOver = currentStatus.gameOver
        winner = currentStatus.winner
        draw = currentStatus.draw
        turn = if (turn == Game.WHITE) Game.BLACK else Game.WHITE
    }

    fun makeMove(boardOutput: BoardOutput) {
        _gameBoard[loc(boardOutput.moveRow, boardOutput.moveCol)] = turn

        gameOver = boardOutput.gameOver
        winner = boardOutput.winner
        draw = boardOutput.draw
        turn = if (turn == Game.WHITE) Game.BLACK else Game.WHITE
    }

    operator fun invoke(row: UInt, col: UInt): Char {
        return _gameBoard[loc(row, col)]
    }

    override fun toString(): String {
        return "${getGameBoard()}/${turn}/${gameOver}/${winner}/${draw}"
    }

    fun getGameBoard(): String {
        return String(_gameBoard)
    }

    fun getGameBoardHashCode(): Int {
        return getGameBoard().hashCode()
    }

    private fun loc(row: UInt, col: UInt): Int {
        return loc(row.toInt(), col.toInt())
    }

    private fun loc(row: Int, col: Int): Int {
        return (row * 3) + col
    }

    fun copy(): Board {
        return Board(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board

        if (!_gameBoard.contentEquals(other._gameBoard)) return false
        if (turn != other.turn) return false
        if (gameOver != other.gameOver) return false
        if (winner != other.winner) return false
        if (draw != other.draw) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _gameBoard.contentHashCode()
        result = 31 * result + turn.hashCode()
        result = 31 * result + gameOver.hashCode()
        result = 31 * result + winner.hashCode()
        result = 31 * result + draw.hashCode()
        return result
    }
}