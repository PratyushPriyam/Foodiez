package com.example.foodiez.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodiez.R
import com.todkars.shimmer.ShimmerRecyclerView

class RecepiesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recepies, container, false)
        val shimmerRecyclerView = view.findViewById<ShimmerRecyclerView>(R.id.recyclerview)
        shimmerRecyclerView.showShimmer()
        return view
    }
}