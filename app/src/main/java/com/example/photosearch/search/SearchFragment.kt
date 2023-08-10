package com.example.photosearch.search

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.leanback.app.SearchSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.ObjectAdapter
import com.example.photosearch.main_screen.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SearchFragment : SearchSupportFragment(), SearchSupportFragment.SearchResultProvider {

    private val mainScreenViewModel: MainScreenViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSearchResultProvider(this)
    }
    override fun getResultsAdapter(): ObjectAdapter = ArrayObjectAdapter(ListRowPresenter())

    override fun onQueryTextChange(newQuery: String?) = false

    override fun onQueryTextSubmit(query: String?): Boolean {
        android.util.Log.d("QUERY RESULT", query.orEmpty())
        mainScreenViewModel.onSearchSubmitted(query.orEmpty())
        return true
    }




}