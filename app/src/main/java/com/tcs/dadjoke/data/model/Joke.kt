package com.tcs.dadjoke.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by astirtrotter on 3/2/22
 */
@Entity
data class Joke(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val joke: String,
    val status: Int,
    val isNew: Boolean
)

