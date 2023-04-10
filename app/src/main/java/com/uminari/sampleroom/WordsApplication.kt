package com.uminari.sampleroom

import android.app.Application

// Application()の役割がよくわかっていない
class WordsApplication: Application() {
    // by lazyは変数が初めて呼び出された時に、初期化される。
    // 次回の呼び出し以降は同じ値を返す。
    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}