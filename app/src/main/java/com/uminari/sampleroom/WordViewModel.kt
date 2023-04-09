package com.uminari.sampleroom

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordViewModel(private val repository: WordRepository): ViewModel() {
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) {
        viewModelScope.launch {
            repository.insert(word)
        }
    }
}

// viewModelのインスタンス化は普通にコンストラクタ呼ぶのではなく、
// ViewModelProvider.Factoryによって実施されている。
// なので、viewModelのコンストラクタに他の依存関係(パラメタ)を追加したい場合はFactoryをカスタムしてあげる。
class WordViewModelFactory(private val repository: WordRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(repository) as T
        } else {
            throw IllegalArgumentException("unknown viewModel class")
        }
    }

}