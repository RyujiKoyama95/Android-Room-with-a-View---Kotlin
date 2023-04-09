package com.uminari.sampleroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // LiveDataのallWordsを監視して、データに変更があった場合に発火する
        wordViewModel.allWords.observe(this, Observer { allWords ->
            // ListAdapterは、RecyclerViewの表示するアイテムの更新は
            // submitList()メソッドを使用してリストをセットする
            allWords.let { adapter.submitList(it) }
        })
    }
}