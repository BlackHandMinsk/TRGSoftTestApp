package com.example.catsapi.ui

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
import com.example.catsapi.databinding.FragmentCatInfoBinding

import com.example.catsapi.placePictureInView

import com.example.catsapi.setBackGroundAnimation
import com.example.catsapi.R
import com.foodrecipesapp.data.database.entities.FavoritesEntity
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

        mBinding.textView.setText(args.url)
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
        checkSavedRecipes(menu.findItem(R.id.save_to_favorites_menu))
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
       // showSnackBar("Recipe saved")
        catSaved = true
    }
///TO DO
    private fun checkSavedRecipes(menuItem: MenuItem) {
        catInfoViewModel.readFavoritesCats().asLiveData().observe(this, { favoriteEntity ->
            try {
                for (cat in favoriteEntity) {
                    if (cat.url == args.url) {
                        changeMenuItemColor(menuItem, R.color.black)
                        savedCatId = cat.url
                        catSaved = true
                    }
                }
            } catch (e: Exception) {
                Log.d("DetailsActivity", e.message.toString())
            }
        })
//        catInfoViewModel.readFavoritesCats().observe(this, { favoriteEntity ->
//            try {
//                for (savedRecipe in favoriteEntity) {
//                    if (savedRecipe.result.id == args.result.id) {
//                        changeMenuItemColor(menuItem, R.color.yellow)
//                        savedRecipeId = savedRecipe.id
//                        recipeSaved = true
//                    }
//                }
//            } catch (e: Exception) {
//                Log.d("DetailsActivity", e.message.toString())
//            }
//        })
    }

    private fun removeFromFavorites(item: MenuItem) {
        val favoritesEntity = FavoritesEntity(savedCatId, args.url)
        catInfoViewModel.deleteFavoriteCats(favoritesEntity)
        changeMenuItemColor(item, R.color.white)
       // showSnackBar("Remove from favorites")
        catSaved = false
    }


//    private fun showSnackBar(message: String) {
//        Snackbar.make(detailsLayout, message, Snackbar.LENGTH_SHORT).setAction("Ok") {}.show()
//    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(requireContext(), color))
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}