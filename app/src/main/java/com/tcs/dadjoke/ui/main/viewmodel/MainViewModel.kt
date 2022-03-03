package com.tcs.dadjoke.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tcs.dadjoke.data.model.Joke
import com.tcs.dadjoke.data.repository.JokeRepository
import com.tcs.dadjoke.data.room.JokeDB
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by astirtrotter on 3/2/22
 */
class MainViewModel: ViewModel() {
    val displayData = MutableLiveData<List<Joke>>()
    val loadingStatusData = MutableLiveData(false)
    private val jokes = arrayListOf<Joke>()
    private val compositeDisposable = CompositeDisposable()
    private var filter = ""

    init {
        load()
    }

    private fun load() {
        loadingStatusData.postValue(true)
        compositeDisposable.add(JokeDB.instance.jokeDao().getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<Joke>>() {
                override fun onSuccess(result: List<Joke>) {
                    jokes.addAll(result)
                    displayData.postValue(jokes)
                    loadingStatusData.postValue(false)
                }

                override fun onError(e: Throwable) {
                    loadingStatusData.postValue(false)
                }
            })
        )
    }

    fun add() {
        loadingStatusData.postValue(true)
        compositeDisposable.add(JokeRepository.getJoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<Joke>() {
                override fun onSuccess(joke: Joke) {
                    jokes.add(joke)
                    JokeDB.instance.jokeDao().insert(joke)
                    search()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    loadingStatusData.postValue(false)
                }
            })
        )
    }

    fun search(filter: String = this.filter) {
        loadingStatusData.postValue(true)
        this.filter = filter
        if (filter.isEmpty()) {
            displayData.postValue(jokes)
        } else {
            displayData.postValue(jokes.filter { it.joke.contains(filter) })
        }
        loadingStatusData.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}