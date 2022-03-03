package com.tcs.dadjoke.data.repository

import com.tcs.dadjoke.data.api.RetrofitClient
import com.tcs.dadjoke.data.model.Joke
import io.reactivex.rxjava3.core.Single
import java.io.IOException

/**
 * Created by astirtrotter on 3/2/22
 */
object JokeRepository {

    @Throws(IOException::class)
    fun getJoke(): Single<Joke> {
        return RetrofitClient.apiService.getJoke()
    }

}