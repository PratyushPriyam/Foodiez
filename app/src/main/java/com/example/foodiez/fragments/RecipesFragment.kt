package com.example.foodiez.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodiez.viewModels.MainViewModel
import com.example.foodiez.R
import com.example.foodiez.adapters.RecipesAdapter
import com.example.foodiez.util.Constants.Companion.API_KEY
import com.example.foodiez.util.NetworkResult
import com.example.foodiez.viewModels.RecipesViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var mView: View
    private lateinit var shimmerRecyclerView: ShimmerRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_recepies, container, false)

        shimmerRecyclerView = mView.findViewById<ShimmerRecyclerView>(R.id.recyclerview)
        shimmerRecyclerView.showShimmer()

        setUpRecyclerView()
        requestApiData()

        return mView
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmer()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmer()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmer()
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        shimmerRecyclerView.adapter = mAdapter
        shimmerRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmer()
    }

    private fun showShimmer() {
        shimmerRecyclerView.showShimmer()
    }

    private fun hideShimmer() {
        shimmerRecyclerView.hideShimmer()
    }
}