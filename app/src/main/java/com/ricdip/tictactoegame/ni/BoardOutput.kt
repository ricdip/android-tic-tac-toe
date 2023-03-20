package com.ricdip.tictactoegame.ni

data class BoardOutput(
    val moveRow: Int,
    val moveCol: Int,
    val gameOver: Boolean,
    val winner: Char,
    val draw: Boolean
)