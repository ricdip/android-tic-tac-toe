package com.ricdip.tictactoegame.ni

object NativeGame {
    external fun getBoardStatus(boardInput: BoardInput): BoardStatus

    external fun getNextAiMove(boardInput: BoardInput, aiDepth: Int): BoardOutput

    init {
        System.loadLibrary("tictactoegame")
    }
}