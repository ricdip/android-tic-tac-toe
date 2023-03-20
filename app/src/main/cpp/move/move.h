#ifndef TICTACTOEGAME_MOVE_H
#define TICTACTOEGAME_MOVE_H

#include <cstdint>

class Move {
private:
    uint8_t x;
    uint8_t y;
public:
    Move(uint8_t, uint8_t);
    uint8_t getX() const;
    uint8_t getY() const;
};

#endif //TICTACTOEGAME_MOVE_H
