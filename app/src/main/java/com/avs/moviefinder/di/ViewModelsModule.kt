package com.avs.moviefinder.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avs.moviefinder.ui.favorites.FavoritesViewModel
import com.avs.moviefinder.ui.search_result.SearchResultViewModel
import com.avs.moviefinder.ui.home.HomeViewModel
import com.avs.moviefinder.ui.movie.MovieViewModel
import com.avs.moviefinder.ui.watch_later.WatchLaterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun movieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WatchLaterViewModel::class)
    internal abstract fun watchLaterViewModel(watchLaterViewModel: WatchLaterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    internal abstract fun favoritesViewModel(favoritesViewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchResultViewModel::class)
    internal abstract fun findDetailViewModel(searchResultViewModel: SearchResultViewModel): ViewModel
}