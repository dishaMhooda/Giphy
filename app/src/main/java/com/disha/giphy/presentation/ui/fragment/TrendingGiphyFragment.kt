package com.disha.giphy.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.disha.giphy.R
import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.GIF
import com.disha.giphy.data.util.Resource
import com.disha.giphy.databinding.FragmentTrendingGiphyBinding
import com.disha.giphy.presentation.ui.adapter.TrendingGiphyAdapter
import com.disha.giphy.presentation.viewmodel.GiphyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TrendingGiphyFragment : Fragment(), TrendingGiphyAdapter.FavouriteClickListener {

    private lateinit var trendingGiphyBinding: FragmentTrendingGiphyBinding

    lateinit var listAdapter: ArrayAdapter<String>
    lateinit var searchedHistory: List<String>

    @Inject
    lateinit var giphyViewModel: GiphyViewModel

    @Inject
    lateinit var trendingGiphyAdapter: TrendingGiphyAdapter
    private var isScrolling = false
    private var isLoading = false
    var offset: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trending_giphy, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trendingGiphyBinding = FragmentTrendingGiphyBinding.bind(view)
        searchedHistory = giphyViewModel.getSearchedHistory()
        initializeSearchHistoryList()
        initializeRecycleView()
        viewTrendingGiphy()
        setSearchView()
    }

    /**
     *  Callback when giphy is clicked to be added to favourite.
     */
    override fun onFavouriteClicked(data: Data) {
        var favouriteGIF = FavouriteGiphy(
            data.id,
            data.images,
            data.title,
            data.trending_datetime,
            data.type,
            data.url,
            data.username
        )
        giphyViewModel.saveGiphyAsFavourite(favouriteGIF)
    }

    /**
     * Initalizes Recyclerview for trending and searched giphy
     */
    private fun initializeRecycleView() {
        trendingGiphyAdapter.favouriteClickListener = this
        val staggeredGridLayoutManager = GridLayoutManager(activity, 2)
        trendingGiphyBinding.rvTrendingGiphy.apply {
            adapter = trendingGiphyAdapter
            layoutManager = staggeredGridLayoutManager
            addOnScrollListener(this@TrendingGiphyFragment.onScrollListener)
        }

    }

    /**
     *  Manages Trending Giphy
     */
    private fun viewTrendingGiphy() {
        giphyViewModel.getAllTrendingGif(offset)
        giphyViewModel.giphy.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    trendingGiphyBinding.progressBar.visibility = View.GONE
                    val convertedData: GIF? = response.data
                    if (convertedData?.data?.size != 0) {
                        trendingGiphyAdapter.differ.submitList(convertedData!!.data)
                    }

                }
                is Resource.Error -> {
                    trendingGiphyBinding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        activity,
                        com.disha.giphy.data.util.Error.TRY.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {
                    trendingGiphyBinding.progressBar.visibility = View.VISIBLE
                }
            }

        }
    }

    /**
     *  Sets searched history to be displayed and also handles click listeners of searchview
     */
    private fun setSearchView() {
        trendingGiphyBinding.searchGiphy.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                trendingGiphyBinding.searchedHistory.visibility = View.GONE
                giphyViewModel.getSearchedGiphy(p0.toString(), 0)
                giphyViewModel.saveSearchedKeyword(p0.toString())
                viewSearchGiphy()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                /* MainScope().launch {
                     delay(2000)
                     giphyViewModel.getSearchedGiphy(p0.toString(), 0)
                     viewSearchGiphy()
                 }*/
                searchedHistory = giphyViewModel.getSearchedHistory()
                listAdapter.notifyDataSetChanged()
                trendingGiphyBinding.searchedHistory.visibility = View.VISIBLE
                return false
            }

        })

        trendingGiphyBinding.searchGiphy.setOnSearchClickListener {
            trendingGiphyBinding.searchedHistory.visibility = View.VISIBLE
            initializeSearchHistoryList()
            searchedHistory = giphyViewModel.getSearchedHistory()
        }

        trendingGiphyBinding.searchGiphy.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                trendingGiphyBinding.searchedHistory.visibility = View.GONE
                initializeRecycleView()
                viewTrendingGiphy()
                return false
            }
        })
    }

    /**
     *  Handles searched giphy
     */
    fun viewSearchGiphy() {
        giphyViewModel.giphy.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    trendingGiphyBinding.progressBar.visibility = View.GONE
                    val convertedData: GIF? = response.data
                    trendingGiphyAdapter.differ.submitList(convertedData!!.data)
                }
                is Resource.Error -> {
                    trendingGiphyBinding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        activity,
                        com.disha.giphy.data.util.Error.TRY.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {
                    trendingGiphyBinding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager =
                trendingGiphyBinding.rvTrendingGiphy.layoutManager as GridLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                offset += 20
                giphyViewModel.getAllTrendingGif(offset)
                isScrolling = false

            }
        }
    }

    /**
     *  Displays searched keyword
     */
    private fun initializeSearchHistoryList() {
        listAdapter = ArrayAdapter<String>(
            giphyViewModel.getApplicationContext(),
            android.R.layout.simple_list_item_1, searchedHistory
        )

        trendingGiphyBinding.searchedHistory.onItemClickListener =
            object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    p3: Long
                ) {
                    trendingGiphyBinding.searchedHistory.visibility = View.GONE
                    val value = adapterView?.getItemAtPosition(position) as String
                    giphyViewModel.getSearchedGiphy(value, 0)
                    viewSearchGiphy()
                }

            }
        trendingGiphyBinding.searchedHistory.adapter = listAdapter
        listAdapter.notifyDataSetChanged()
    }
}