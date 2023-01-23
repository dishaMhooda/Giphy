package com.disha.giphy.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.disha.giphy.R
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.databinding.FragmentFavouriteGiphyBinding
import com.disha.giphy.presentation.ui.adapter.FavouriteGiphyAdapter
import com.disha.giphy.presentation.viewmodel.GiphyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteGiphyFragment : Fragment(), FavouriteGiphyAdapter.FavouriteClickListener {

    private lateinit var favouriteGiphyBinding: FragmentFavouriteGiphyBinding

    @Inject
    lateinit var giphyViewModel: GiphyViewModel

    @Inject
    lateinit var favouriteGiphyAdapter: FavouriteGiphyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_giphy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favouriteGiphyBinding = FragmentFavouriteGiphyBinding.bind(view)
        initializeRecyclerView()
        viewFavouriteGiphy()
    }

    private fun initializeRecyclerView() {
        favouriteGiphyAdapter.setFavouriteListener(this)
        favouriteGiphyBinding.rvFavouriteGiphy.adapter = favouriteGiphyAdapter

        val staggeredGridLayoutManager = GridLayoutManager(activity, 2)
        favouriteGiphyBinding.rvFavouriteGiphy.layoutManager = staggeredGridLayoutManager

    }

    private fun viewFavouriteGiphy() {
        //  giphyViewModel.
        giphyViewModel.getFavouriteGiphy().observe(viewLifecycleOwner) {
            favouriteGiphyAdapter.differ.submitList(it)
        }

    }

    override fun onFavouriteClicked(favourite: FavouriteGiphy) {
        Toast.makeText(activity, "Removed giphy from favourite", Toast.LENGTH_SHORT).show()
        giphyViewModel.removeGiphyFromFavourite(favourite)
    }

}