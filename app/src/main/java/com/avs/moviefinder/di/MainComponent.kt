package com.avs.moviefinder.di

import com.avs.moviefinder.ui.main.MainActivity
import com.avs.moviefinder.ui.find.FindFragment
import com.avs.moviefinder.ui.find_detail.FindDetailFragment
import com.avs.moviefinder.ui.watch_later.WatchLaterFragment
import com.avs.moviefinder.ui.watched.WatchedFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: FindFragment)
    fun inject(fragment: WatchedFragment)
    fun inject(fragment: WatchLaterFragment)
    fun inject(fragment: FindDetailFragment)
}