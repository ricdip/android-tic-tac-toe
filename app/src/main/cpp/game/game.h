#ifndef TICTACTOEGAME_GAME_H
#define TICTACTOEGAME_GAME_H

#include "../board/board.h"

#include "defs.h"

class Game {
private:
    uint32_t maxDepth;
    static std::vector<Board> neighbors(const Board &);
    int32_t minmaxAlphaBetaAux(const Board &, uint32_t, int32_t, int32_t, bool);
    Board minmaxAlphaBeta(const Board &, bool);
public:
    Game(uint32_t);
    Board nextState(const Board &);
};

#endif //TICTACTOEGAME_GAME_H
