package com.avs.moviefinder.ui.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avs.moviefinder.R
import com.avs.moviefinder.databinding.FragmentFavoritesBinding
import com.avs.moviefinder.di.ViewModelFactory
import com.avs.moviefinder.ui.BaseFragment
import com.avs.moviefinder.ui.find_detail.FindDetailViewModel
import com.avs.moviefinder.ui.recycler_view.BaseMoviesAdapter
import com.avs.moviefinder.ui.recycler_view.MovieListener
import javax.inject.Inject

class FavoritesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var favoritesViewModel: FavoritesViewModel

    private lateinit var binding: FragmentFavoritesBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        favoritesViewModel =
            ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favorites, container, false
        )
        val root: View = binding.root
        binding.favoritesViewModel = favoritesViewModel
        binding.lifecycleOwner = this
        val adapter = BaseMoviesAdapter(
            MovieListener(
                { movie -> startMovieActivity(movie) },
                { movieId -> favoritesViewModel.shareMovie(movieId) },
                { movieId -> favoritesViewModel.addToWatched(movieId) },
                { movieId -> favoritesViewModel.addToWatchLater(movieId) })
        )
        favoritesViewModel.movies.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        favoritesViewModel.isProgressVisible.observe(viewLifecycleOwner, {
            binding.pbFetchingProgress.visibility = if (it) View.VISIBLE else View.INVISIBLE
        })
        binding.rvFindRecyclerView.adapter = adapter
        return root
    }

    override fun onResume() {
        super.onResume()
        favoritesViewModel.fetchFavoriteMovies()
    }
}