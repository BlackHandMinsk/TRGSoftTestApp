package com.example.catsapi.ui.fragments

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

import com.example.catsapi.databinding.FragmentMainBinding
import com.example.catsapi.utils.setBackGroundAnimation
import com.example.catsapi.ui.viewmodels.MainViewModel
import com.example.catsapi.ui.adapters.CatsAdapter
import com.example.catsapi.ui.adapters.LoaderStateAdapter

import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@ExperimentalPagingApi
@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private  var mAdapter: CatsAdapter = CatsAdapter()
    private var loaderStateAdapter: LoaderStateAdapter = LoaderStateAdapter { mAdapter.retry() }

    private val mainViewModel: MainViewModel by viewModels()






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        setUpViews(view)
        fetchCatGoImages()
         setBackGroundAnimation(mBinding.mainLayout.background as AnimationDrawable)



        return mBinding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        mBinding.catsList.adapter = null
        _binding = null
    }

    private fun setUpViews(view: View?) {
        mBinding.catsList.layoutManager = GridLayoutManager(context, 2)
        mBinding.catsList.adapter = mAdapter.withLoadStateFooter(loaderStateAdapter)
    }


    private fun fetchCatGoImages() {
        lifecycleScope.launch {
            mainViewModel.fetchCatGoImages().distinctUntilChanged().collectLatest {
                mAdapter.submitData(it)
            }
        }
}
}

