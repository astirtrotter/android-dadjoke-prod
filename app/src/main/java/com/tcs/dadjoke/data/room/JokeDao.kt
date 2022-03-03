package com.tcs.dadjoke.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tcs.dadjoke.data.model.Joke
import io.reactivex.rxjava3.core.Single

/**
 * Created by astirtrotter on 3/3/22
 */
@Dao
interface JokeDao {
    @Query("SELECT * FROM joke")
    fun getAll(): Single<List<Joke>>

    @Insert
    fun insert(joke: Joke)
}