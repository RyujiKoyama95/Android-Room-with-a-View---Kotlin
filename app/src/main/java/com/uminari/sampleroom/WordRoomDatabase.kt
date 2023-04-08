package com.uminari.sampleroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        // データベースのインスタンスが複数同時に開かれるのを防ぐために、
        // シングルトンのWordRoomDatabaseを定義

        // 以下のinstance定義はcompanion objectブロック外にあっても問題ないのか？
        private var INSTANCE: WordRoomDatabase? = null

        // getDatabase()はcompanion Objectブロック内にあるので、シングルトンになるのか？
        fun getDatabase(context: Context): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}