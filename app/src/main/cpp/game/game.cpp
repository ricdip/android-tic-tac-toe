#include "game.h"

#include <vector>
#include "heuristic.h"

Game::Game(uint32_t d) : maxDepth(d) {}

Board Game::nextState(const Board &board) {
    return minmaxAlphaBeta(board, board.getTurn());
}

std::vector<Board> Game::neighbors(const Board &board) {
    std::vector<Board> states = std::vector<Board>();

    std::vector<Move> moves = board.getMoves();
    for (auto & move : moves) {
        states.push_back(board.makeMove(move));
    }

    return states;
}

int32_t Game::minmaxAlphaBetaAux(const Board &board, uint32_t depth, int32_t alpha, int32_t beta, bool turn) {
    if (depth == 0 || board.isGameOver()) {
        return Heuristic::H(board);
    }
    if (turn) {
        // max player = WHITE
        int32_t value = INT32_MIN;
        std::vector<Board> children = Game::neighbors(board);
        for (auto & child : children) {
            value = std::max(
                    value, minmaxAlphaBetaAux(child, depth - 1, alpha, beta, BLACK_TURN));
            if (value > beta) {
                // beta cutoff
                break;
            }
            alpha = std::max(alpha, value);
        }
        return value;
    } else {
        // min player = BLACK
        int32_t value = INT32_MAX;
        std::vector<Board> children = Game::neighbors(board);
        for (auto & child : children) {
            value = std::min(
                    value, minmaxAlphaBetaAux(child, depth - 1, alpha, beta, WHITE_TURN));
            if (value < alpha) {
                // alpha cutoff
                break;
            }
            beta = std::min(beta, value);
        }
        return value;
    }
}

Board Game::minmaxAlphaBeta(const Board &board, bool turn) {
    // always first call with alpha = -infty, beta = +infty
    int32_t alpha = INT32_MIN;
    int32_t beta = INT32_MAX;

    std::vector<Board> children = Game::neighbors(board);

    int32_t bestStateValue = (turn) ? INT32_MIN : INT32_MAX;
    Board bestState = children[0];

    if (turn) {
        // max player = WHITE
        for (auto & child : children) {
            int32_t value =
                    minmaxAlphaBetaAux(child, maxDepth - 1, alpha, beta, BLACK_TURN);
            if (value > bestStateValue) {
                bestStateValue = value;
                bestState = child;
            }
            alpha = std::max(alpha, value);
        }
    } else {
        // min player = BLACK
        for (auto & child : children) {
            int32_t value =
                    minmaxAlphaBetaAux(child, maxDepth - 1, alpha, beta, WHITE_TURN);
            if (value < bestStateValue) {
                bestStateValue = value;
                bestState = child;
            }
            beta = std::min(beta, value);
        }
    }

    return bestState;
}