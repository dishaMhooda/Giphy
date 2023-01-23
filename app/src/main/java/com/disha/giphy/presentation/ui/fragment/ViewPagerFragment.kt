package com.disha.giphy.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.disha.giphy.R
import com.disha.giphy.databinding.FragmentViewPagerBinding
import com.disha.giphy.presentation.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment : Fragment() {

    lateinit var videoPagerFragmentViewPagerBinding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoPagerFragmentViewPagerBinding = FragmentViewPagerBinding.bind(view)

        val fragmentList = arrayListOf<Fragment>(
            TrendingGiphyFragment(),
            FavouriteGiphyFragment(),

            )

        val tabHeading = arrayListOf<String>(
            "Trending",
            "Favourite"
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        videoPagerFragmentViewPagerBinding.viewPager.adapter = adapter

        TabLayoutMediator(
            videoPagerFragmentViewPagerBinding.tabLayout,
            videoPagerFragmentViewPagerBinding.viewPager
        ) { tab, position ->
            tab.text = tabHeading[position]
        }.attach()
    }
}