package com.example.weatheracc.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weatheracc.R
import com.example.weatheracc.adapters.SearchListAdapter
import com.example.weatheracc.viewModels.SearchViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.search_fragment.view.*
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<SearchViewModel> { factory }

    private val searchListAdapter by lazy {
        SearchListAdapter {
            viewModel.storeCity(it)
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSavedCityFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_fragment, container, false)
            .apply {
                btnConfirm.setOnClickListener { viewModel.searchCity(etSearch.text.toString()) }
                btnBack.setOnClickListener { findNavController().popBackStack() }
                rvCity.adapter = searchListAdapter

                with(viewModel) {
                    cityList.observe(viewLifecycleOwner, Observer {
                        if (it.isNotEmpty()) {
                            searchListAdapter.submitList(it)
                            handleVisibility(textView, rvCity, false)
                        } else {
                            textView.text = "List is empty"
                            handleVisibility(textView, rvCity, true)
                        }
                    })
                    errorMessage.observe(viewLifecycleOwner, Observer {
                        textView.text = it
                        handleVisibility(textView, rvCity, true)
                    })
                }
            }
    }

    private fun handleVisibility(
        textView: View,
        recyclerView: RecyclerView,
        shouldShowError: Boolean
    ) {
        textView.visibility = if (shouldShowError) View.VISIBLE else View.GONE
        recyclerView.visibility = if (shouldShowError) View.GONE else View.VISIBLE
    }
}
