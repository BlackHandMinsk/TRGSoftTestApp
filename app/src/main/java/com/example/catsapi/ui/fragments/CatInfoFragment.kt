package com.example.catsapi.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap

import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi


import com.example.catsapi.utils.placePictureInView

import com.example.catsapi.utils.setBackGroundAnimation
import com.example.catsapi.R
import com.example.catsapi.databinding.FragmentCatInfoBinding
import com.example.catsapi.ui.viewmodels.CatInfoViewModel
import com.example.catsapi.data.local.entities.FavoritesEntity
import dagger.hilt.android.AndroidEntryPoint

import kotlin.random.Random

@ExperimentalPagingApi
@AndroidEntryPoint
class CatInfoFragment : Fragment(){

    private var _binding: FragmentCatInfoBinding? = null
    private val mBinding get() = _binding!!
    private val args by navArgs<CatInfoFragmentArgs>()
    @ExperimentalPagingApi
    private val catInfoViewModel: CatInfoViewModel by viewModels()



    private var catSaved = false
    private var savedCatId = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatInfoBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        mBinding.textView.text = args.url
        placePictureInView(mBinding.ivCatInfo,args.url)
        setBackGroundAnimation(mBinding.catInfoLayout.background as AnimationDrawable)

        mBinding.saveImageBtn.setOnClickListener {
        saveImage(mBinding.ivCatInfo.drawToBitmap())
        }





        return mBinding.root
    }


    private fun saveImage(bitmap: Bitmap){

        val imageFileName = "CAT_" + Random.nextInt()
        val savedImageURL: String = MediaStore.Images.Media.insertImage(
            requireContext().contentResolver,
            bitmap,
            imageFileName,
            "CaT Image"
        )
        Handler().postDelayed({
            Toast.makeText(requireContext(),"Image Downloaded",Toast.LENGTH_LONG).show()
        },1000*5)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        checkSavedCat(menu.findItem(R.id.save_to_favorites_menu))
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if (item.itemId == R.id.save_to_favorites_menu && !catSaved) {
            saveToFavorites(item)
        } else if (item.itemId == R.id.save_to_favorites_menu && catSaved) {
            removeFromFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    @ExperimentalPagingApi
    private fun saveToFavorites(item: MenuItem) {
        val favoritesEntity = FavoritesEntity(args.url,
            args.url
        )
        catInfoViewModel.addToFavorite(favoritesEntity)
        changeMenuItemColor(item, R.color.black)
        Toast.makeText(requireContext(),"Cat saved to favorites",Toast.LENGTH_SHORT).show()
        catSaved = true
    }

    private fun checkSavedCat(menuItem: MenuItem) {
        catInfoViewModel.readFavoritesCats().asLiveData().observe(this, { favoriteEntity ->
            try {
                for (cat in favoriteEntity) {
                    if (cat.url == args.url) {
                        changeMenuItemColor(menuItem, R.color.yellow)
                        savedCatId = cat.url
                        catSaved = true
                    }
                }
            } catch (e: Exception) {
                Log.d("Check", e.message.toString())
            }
        })
    }

    private fun removeFromFavorites(item: MenuItem) {
        val favoritesEntity = FavoritesEntity(savedCatId, args.url)
        catInfoViewModel.deleteFavoriteCats(favoritesEntity)
        changeMenuItemColor(item, R.color.white)
        Toast.makeText(requireContext(),"Remove from favorites",Toast.LENGTH_SHORT).show()
        catSaved = false
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(requireContext(), color))
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}