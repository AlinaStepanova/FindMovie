package com.avs.moviefinder.ui.watched

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.avs.moviefinder.R
import com.avs.moviefinder.ui.main.MainActivity
import javax.inject.Inject

class WatchedFragment : Fragment() {

    @Inject
    lateinit var watchedViewModel: WatchedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_watched, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        watchedViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}