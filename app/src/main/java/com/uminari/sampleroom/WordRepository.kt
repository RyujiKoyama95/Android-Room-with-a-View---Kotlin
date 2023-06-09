package com.uminari.sampleroom

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// WordDaoはコンストラクタのパラメタでないとダメな理由がよく分からん。
// 普通にメンバ変数ではダメなのか？(Repositoryのインスタンスも1つにする必要がる？)
// →依存関係があるobjectはコンストラクタで紐付ける方がいい。(コンストラクタでメンバ変数定義もしている)
// 普通のメンバ変数にすると、repositoryのインスタンス後に、追加でwordDaoに値をセットしないといけない。
// コンストラクタにwordDaoを含めれば、インスタンスと同時に初期化もできるので、スッキリする。
class WordRepository(private val wordDao: WordDao) {
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // 以下のアノテーションは必要ない？
    // 何の警告を消すためのアノテーションなのか分からん
    @Suppress("RedundantSuspendModifier")
    // メソッドの実行されるスレッドを明示
    // WorkerThreadでUI処理を行おうとするとエラーとなる。
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}