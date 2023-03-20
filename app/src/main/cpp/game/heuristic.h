#ifndef TICTACTOEGAME_HEURISTIC_H
#define TICTACTOEGAME_HEURISTIC_H

#include "../board/board.h"

namespace Heuristic {
    int32_t H(const Board &);
    int32_t gameOverH(const Board &);
}

#endif //TICTACTOEGAME_HEURISTIC_H
