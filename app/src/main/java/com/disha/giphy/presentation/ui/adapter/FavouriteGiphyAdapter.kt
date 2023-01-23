package com.disha.giphy.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.databinding.FavouriteGiphyItemBinding

class FavouriteGiphyAdapter() :
    RecyclerView.Adapter<FavouriteGiphyAdapter.FavouriteGiphyViewHolder>() {


    private lateinit var favouriteClickListener: FavouriteClickListener
    private val callback = object : DiffUtil.ItemCallback<FavouriteGiphy>() {
        override fun areItemsTheSame(oldItem: FavouriteGiphy, newItem: FavouriteGiphy): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavouriteGiphy, newItem: FavouriteGiphy): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)

    inner class FavouriteGiphyViewHolder(private val favouriteGiphyItemBinding: FavouriteGiphyItemBinding) :
        RecyclerView.ViewHolder(favouriteGiphyItemBinding.root) {
        fun bind(favourite: FavouriteGiphy) {
            Glide.with(favouriteGiphyItemBinding.imgGiphy.context)
                .load(favourite.images.original.url)
                .into(favouriteGiphyItemBinding.imgGiphy)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteGiphyViewHolder {
        val binding =
            FavouriteGiphyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteGiphyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteGiphyViewHolder, position: Int) {
        val favouriteData = differ.currentList[position]
        var data = favouriteData;
        holder.bind(favouriteData)

        holder.itemView.setOnClickListener {
            favouriteClickListener.onFavouriteClicked(data)
        }

    }

    override fun getItemCount(): Int {
        var size = differ.currentList?.size
        // Log.i("Size", size.toString())
        if (size!! > 0) {
            size = differ.currentList.size
            Log.i("Size", size.toString())
            return size
        }
        return 0
    }

    fun setFavouriteListener(favouriteClickListener: FavouriteGiphyAdapter.FavouriteClickListener) {
        this.favouriteClickListener = favouriteClickListener
    }

    interface FavouriteClickListener {
        fun onFavouriteClicked(favourite: FavouriteGiphy)
    }

}