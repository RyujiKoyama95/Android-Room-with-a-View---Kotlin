package com.uminari.sampleroom

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// WordDaoはコンストラクタのパラメタでないとダメな理由がよく分からん。
// 普通にメンバ変数ではダメなのか？(Repositoryのインスタンスも1つにする必要がる？)
class WordRepository(private val wordDao: WordDao) {
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // 以下のアノテーションは必要ない？
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}