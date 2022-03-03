package com.tcs.dadjoke

import android.app.Application
import com.tcs.dadjoke.data.room.JokeDB

/**
 * Created by astirtrotter on 3/3/22
 */
class JokeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        JokeDB.init(this)
    }
}