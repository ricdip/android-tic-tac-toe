#include "jniutils.h"
#include "../game/defs.h"

void JNIUtils::throwException(JNIEnv *env, const char *msg) {
    jclass exception = env->FindClass("java/lang/Exception");
    env->ThrowNew(exception, msg);
}

Board JNIUtils::boardInputToBoard(JNIEnv *env, const jobject &boardInput) {
    jfieldID boardFid = env->GetFieldID(env->GetObjectClass(boardInput), "boardString", "Ljava/lang/String;");
    auto boardString = (jstring) env->GetObjectField(boardInput, boardFid);
    const char *c = env->GetStringUTFChars(boardString, nullptr);

    jfieldID turnFid = env->GetFieldID(env->GetObjectClass(boardInput), "turn", "C");
    jchar turn = env->GetCharField(boardInput, turnFid);

    return {c, (turn == WHITE) ? WHITE_TURN : BLACK_TURN};
}

jobject JNIUtils::createBoardStatus(JNIEnv *env, const Board &board) {
    jclass cl = env->FindClass("com/ricdip/tictactoegame/ni/BoardStatus");
    if(cl == nullptr) {
        throwException(env, "BoardStatus class not found");
    }

    jmethodID cid = env->GetMethodID(cl, "<init>", "(ZCZ)V");

    return env->NewObject(cl, cid, board.isGameOver(), board.getWinner(), board.isDraw());
}

jobject JNIUtils::createBoardOutput(JNIEnv *env, const Board &board, const Move &move) {
    jclass cl = env->FindClass("com/ricdip/tictactoegame/ni/BoardOutput");
    if(cl == nullptr) {
        throwException(env, "BoardOutput class not found");
    }

    jmethodID cid = env->GetMethodID(cl, "<init>", "(IIZCZ)V");

    return env->NewObject(cl, cid, move.getX(), move.getY(), board.isGameOver(), board.getWinner(), board.isDraw());
}