package com.avs.moviefinder.ui.watch_later

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avs.moviefinder.data.database.DatabaseManager
import com.avs.moviefinder.data.dto.Movie
import com.avs.moviefinder.utils.BASE_URL
import com.avs.moviefinder.utils.RxBus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class WatchLaterViewModel @Inject constructor(
    rxBus: RxBus,
    private val databaseManager: DatabaseManager
) : ViewModel() {

    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies
    private var _isProgressVisible = MutableLiveData<Boolean>()
    val isProgressVisible: LiveData<Boolean>
        get() = _isProgressVisible
    private var _shareBody = MutableLiveData<String?>()
    val shareBody: LiveData<String?>
        get() = _shareBody
    private val dbDisposable = CompositeDisposable()
    private var rxBusDisposable: Disposable? = null

    init {
        rxBusDisposable = rxBus.events.subscribe { event -> handleDBResponse(event) }
    }

    private fun handleDBResponse(event: Any) {
        if (event is List<*>) {
            _isProgressVisible.value = false
            val query = event as List<Movie>
            if (query != _movies.value) {
                _movies.value = query
            }
        }
    }

    fun fetchWatchLaterList() {
        _isProgressVisible.value = true
        dbDisposable.add(databaseManager.getWatchLaterList())
    }

    override fun onCleared() {
        dbDisposable.dispose()
        rxBusDisposable?.dispose()
        super.onCleared()
    }

    fun shareMovie(movieId: Long) {
        _shareBody.value = BASE_URL + "movie/" + movieId + "/"
        _shareBody.value = null
    }

    fun addToWatchLater(movieId: Long) {}

    fun addFavorites(movieId: Long) {}
}