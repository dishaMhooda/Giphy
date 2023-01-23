package com.disha.giphy.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.disha.giphy.R
import com.disha.giphy.data.model.Data
import com.disha.giphy.databinding.TrendingGiphyItemsBinding

class TrendingGiphyAdapter() :
    RecyclerView.Adapter<TrendingGiphyAdapter.TrendingGiphyViewHolder>() {
    private lateinit var binding: TrendingGiphyItemsBinding
    var favouriteClickListener: FavouriteClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingGiphyViewHolder {
        binding =
            TrendingGiphyItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingGiphyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingGiphyViewHolder, position: Int) {
        val giphyData = differ.currentList[position]
        holder.bind(giphyData)
    }

    private val callback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)

    inner class TrendingGiphyViewHolder(private val trendingGiphyItemsBinding: TrendingGiphyItemsBinding) :
        RecyclerView.ViewHolder(trendingGiphyItemsBinding.root) {

        fun bind(data: Data) {
            Glide.with(trendingGiphyItemsBinding.imgGiphy.context)
                .load(data.images.original.url)
                .placeholder(R.drawable.ic_downloading)
                .into(trendingGiphyItemsBinding.imgGiphy)

            trendingGiphyItemsBinding.imgFavourite.setOnClickListener {
                trendingGiphyItemsBinding.imgFavourite.setImageResource(R.drawable.ic_favourite_on)
                favouriteClickListener?.onFavouriteClicked(data)
            }
        }

    }

    override fun getItemCount(): Int {
        var size = differ.currentList?.size
        if (size!! > 0) {
            size = differ.currentList.size
            return size
        }
        return 0
    }

    /**
     * Listener for adding giphy to favourite
     */
    interface FavouriteClickListener {
        fun onFavouriteClicked(data: Data)

    }

    fun setFavouriteListener(favouriteClickListener: FavouriteClickListener) {
        this.favouriteClickListener = favouriteClickListener
    }
}