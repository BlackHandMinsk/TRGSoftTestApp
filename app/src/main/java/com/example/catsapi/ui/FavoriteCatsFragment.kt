package com.example.catsapi.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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
        setUpViews()
        fetchCatGoImages()
        setBackGroundAnimation(mBinding.favoriteCatsLayout.background as AnimationDrawable)



        return mBinding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_all_favorite_cats_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        removeAllFromFavorites()
        Toast.makeText(requireContext(),"All cats removed(",Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }

    private fun removeAllFromFavorites() {
        favoriteCatsViewModel.deleteAllFavoriteCats()
    }


    private fun setUpViews() {
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