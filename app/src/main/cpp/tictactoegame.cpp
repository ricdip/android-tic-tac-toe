#include "utils/jniutils.h"
#include "board/board.h"
#include "game/game.h"

extern "C"
JNIEXPORT jobject JNICALL
Java_com_ricdip_tictactoegame_ni_NativeGame_getBoardStatus(JNIEnv *env, jobject /* thiz */,
                                                           jobject boardInput) {
    try {
        // 1. convert boardInput (Kotlin class BoardInput: jobject) in Board (C++ class)
        Board board = JNIUtils::boardInputToBoard(env, boardInput);

        // 2. create BoardStatus (Kotlin class BoardStatus: jobject)
        jobject boardStatus = JNIUtils::createBoardStatus(env, board);

        // 3. return BoardStatus to Kotlin
        return boardStatus;

    } catch(const std::exception &e) {
        JNIUtils::throwException(env, e.what());
    }

    return nullptr;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_ricdip_tictactoegame_ni_NativeGame_getNextAiMove(JNIEnv *env, jobject /* thiz */,
                                                          jobject boardInput, jint aiDepth) {
    try {
        // 1. convert boardInput (Kotlin class BoardInput: jobject) in Board (C++ class)
        Board board = JNIUtils::boardInputToBoard(env, boardInput);

        // 2. convert aiDepth (JNI type jint) in uint_32 (C++ type uint 32-bit)
        auto maxDepth = (uint32_t) aiDepth;

        // 3. run MinMax Alpha-Beta algorithm and get new Board
        Game game(maxDepth);
        Board nextState = game.nextState(board);

        if(!nextState.getLastMove().has_value()) {
            throw std::runtime_error("No new move for new state");
        }
        Move nextMove = nextState.getLastMove().value();

        // 4. create BoardOutput (Kotlin class BoardOutput: jobject)
        jobject boardOutput = JNIUtils::createBoardOutput(env, nextState, nextMove);

        // 5. return BoardOutput to Kotlin
        return boardOutput;

    } catch(const std::exception &e) {
        JNIUtils::throwException(env, e.what());
    }

    return nullptr;
}