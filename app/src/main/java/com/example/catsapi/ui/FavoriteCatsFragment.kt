package com.example.catsapi.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catsapi.R
import com.example.catsapi.databinding.FragmentFavoriteCatsBinding
import com.example.catsapi.databinding.FragmentMainBinding
import com.example.catsapi.setBackGroundAnimation
import com.example.catsapi.ui.adapter.CatsAdapter
import com.example.catsapi.ui.adapter.FavoriteCatsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class FavoriteCatsFragment : Fragment() {
    private var _binding: FragmentFavoriteCatsBinding? = null
    private val mBinding get() = _binding!!
    private  var mAdapter: FavoriteCatsAdapter = FavoriteCatsAdapter()


    private val favoriteCatsViewModel:FavoriteCatsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteCatsBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        setUpViews(view)
        fetchCatGoImages()
        setBackGroundAnimation(mBinding.favoriteCatsLayout.background as AnimationDrawable)



        return mBinding.root
    }


    private fun setUpViews(view: View?) {
        mBinding.favoritesCatsList.layoutManager = GridLayoutManager(context, 2)
        mBinding.favoritesCatsList.adapter = mAdapter
    }



    override fun onDestroyView() {
        super.onDestroyView()
        mBinding.favoritesCatsList.adapter = null
        _binding = null
    }


    private fun fetchCatGoImages() {
        lifecycleScope.launch {
            favoriteCatsViewModel.fetchCatGoImages().distinctUntilChanged().collectLatest {
                mAdapter.submitList(it)
            }
        }
    }

}