package com.ricdip.tictactoegame.ni

data class BoardStatus(
    var gameOver: Boolean,
    var winner: Char,
    var draw: Boolean
)
