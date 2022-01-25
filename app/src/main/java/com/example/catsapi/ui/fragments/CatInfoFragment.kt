package com.example.catsapi.ui.fragments

import android.Manifest
import android.graphics.Bitmap
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
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
import android.os.Environment
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import android.app.Activity




@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
        verifyStoragePermissions(requireActivity())
        mBinding.textView.text = args.url
        placePictureInView(mBinding.ivCatInfo,args.url)
        setBackGroundAnimation(mBinding.catInfoLayout.background as AnimationDrawable)

        mBinding.saveImageBtn.setOnClickListener {
               saveImageToDownloadFolder(args.url.substring(args.url.lastIndexOf("/")+1),mBinding.ivCatInfo.drawToBitmap())
            }





        return mBinding.root
    }



    private fun saveImageToDownloadFolder(imageFile: String, bitmap: Bitmap) {
        try {
            val filePath = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                imageFile
            )
            val outputStream: OutputStream = FileOutputStream(filePath)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            Toast.makeText(
                requireContext(),
                imageFile + "Saved in Download Folder",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
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


    private fun verifyStoragePermissions(activity: Activity?) {

        val permission = ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
         if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }

    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
}