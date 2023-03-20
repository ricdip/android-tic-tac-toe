#include "move.h"

Move::Move(uint8_t a, uint8_t b) : x(a), y(b) {}

uint8_t Move::getX() const {
    return x;
}

uint8_t Move::getY() const {
    return y;
}