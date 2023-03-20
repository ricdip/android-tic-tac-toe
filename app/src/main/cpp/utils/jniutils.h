#ifndef TICTACTOEGAME_JNIUTILS_H
#define TICTACTOEGAME_JNIUTILS_H

#include <jni.h>
#include "../board/board.h"
#include "../move/move.h"

namespace JNIUtils {
    void throwException(JNIEnv *, const char *);
    Board boardInputToBoard(JNIEnv *, const jobject &);
    jobject createBoardStatus(JNIEnv *, const Board &);
    jobject createBoardOutput(JNIEnv *, const Board &, const Move &);
}

#endif //TICTACTOEGAME_JNIUTILS_H
