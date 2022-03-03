package com.tcs.dadjoke.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tcs.dadjoke.data.model.Joke

/**
 * Created by astirtrotter on 3/3/22
 */
@Database(entities = [Joke::class], version = 1)
abstract class JokeDB: RoomDatabase() {
    abstract fun jokeDao(): JokeDao

    companion object {
        lateinit var instance: JokeDB
            private set
        fun init(context: Context) {
            instance = Room.databaseBuilder(
                context,
                JokeDB::class.java, "joke-db"
            ).build()
        }
    }
}