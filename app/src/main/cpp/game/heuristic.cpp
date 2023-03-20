#include "heuristic.h"

int32_t Heuristic::H(const Board &board) {
    return gameOverH(board);
}

int32_t Heuristic::gameOverH(const Board &board) {
    char winner = board.getWinner();

    if(winner == WHITE) {
        return INT32_MAX;
    } else if(winner == BLACK) {
        return INT32_MIN;
    } else {
        return 0;
    }
}