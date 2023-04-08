package com.uminari.sampleroom

import android.app.Application

// Application()の役割がよくわかっていない
class WordsApplication: Application() {
    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}