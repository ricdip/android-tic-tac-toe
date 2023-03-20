#ifndef TICTACTOEGAME_BOARD_H
#define TICTACTOEGAME_BOARD_H

#include <string>
#include <optional>
#include "../move/move.h"
#include "../game/defs.h"

class Board {
private:
    std::string board;
    bool turn;
    std::optional<Move> lastMove;

    char &operator()(uint8_t x, uint8_t y);
public:
    Board(const char * b, bool);
    std::string getBoard() const;
    bool getTurn() const;
    bool isGameOver() const;
    bool isDraw() const;
    char getWinner() const;

    std::vector<Move> getMoves() const;
    Board makeMove(Move &) const;
    std::optional<Move> getLastMove() const;

    const char &operator()(uint8_t x, uint8_t y) const;
};

#endif //TICTACTOEGAME_BOARD_H
