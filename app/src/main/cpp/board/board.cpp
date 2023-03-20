#include "board.h"
#include <vector>

Board::Board(const char * b, bool t) : board(b), turn(t) {}

std::string Board::getBoard() const {
    return board;
}

bool Board::getTurn() const {
    return turn;
}

bool Board::isGameOver() const {
    if(getWinner() != NONE) {
        return true;
    }

    for(const char & c : board) {
        if(c == NONE) {
            return false;
        }
    }

    return true;
}

bool Board::isDraw() const {
    return getWinner() == NONE && isGameOver();
}

char Board::getWinner() const {
    if((*this)(0, 0) == (*this)(0, 1) && (*this)(0, 1) == (*this)(0, 2)) {
        return (*this)(0, 0);
    } else if((*this)(1, 0) == (*this)(1, 1) && (*this)(1, 1) == (*this)(1, 2)) {
        return (*this)(1, 0);
    } else if((*this)(2, 0) == (*this)(2, 1) && (*this)(2, 1) == (*this)(2, 2)) {
        return (*this)(2, 0);

    } else if((*this)(0, 0) == (*this)(1, 0) && (*this)(1, 0) == (*this)(2, 0)) {
        return (*this)(0, 0);
    } else if((*this)(0, 1) == (*this)(1, 1) && (*this)(1, 1) == (*this)(2, 1)) {
        return (*this)(0, 1);
    } else if((*this)(0, 2) == (*this)(1, 2) && (*this)(1, 2) == (*this)(2, 2)) {
        return (*this)(0, 2);

    } else if((*this)(0, 0) == (*this)(1, 1) && (*this)(1, 1) == (*this)(2, 2)) {
        return (*this)(0, 0);
    } else if((*this)(0, 2) == (*this)(1, 1) && (*this)(1, 1) == (*this)(2, 0)) {
        return (*this)(0, 2);

    } else {
        return NONE;
    }
}

std::vector<Move> Board::getMoves() const {
    std::vector<Move> moves = std::vector<Move>();

    for(uint8_t i = 0; i < MAX_ROWS; i++) {
        for(uint8_t j = 0; j < MAX_COLS; j++) {
            if((*this)(i, j) == NONE) {
                moves.push_back(Move(i, j));
            }
        }
    }

    return moves;
}

Board Board::makeMove(Move &move) const {
    if((*this)(move.getX(), move.getY()) != NONE) {
        throw std::runtime_error("Illegal move");
    }

    Board newBoard = Board(board.c_str(), !turn);
    newBoard(move.getX(), move.getY()) = (turn) ? WHITE : BLACK;
    newBoard.lastMove = move;

    return newBoard;
}

std::optional<Move> Board::getLastMove() const {
    return lastMove;
}

const char &Board::operator()(uint8_t x, uint8_t y) const {
    uint8_t loc = (x * 3) + y;
    return board[loc];
}

char &Board::operator()(uint8_t x, uint8_t y) {
    uint8_t loc = (x * 3) + y;
    return board[loc];
}