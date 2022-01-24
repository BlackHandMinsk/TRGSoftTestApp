package com.example.catsapi.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.drawToBitmap
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.example.catsapi.databinding.FragmentCatInfoBinding

import com.example.catsapi.placePictureInView

import com.example.catsapi.setBackGroundAnimation
import com.bumptech.glide.request.target.SimpleTarget
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import kotlin.random.Random

@AndroidEntryPoint
class CatInfoFragment : Fragment(){

    private var _binding: FragmentCatInfoBinding? = null
    private val mBinding get() = _binding!!
    private val args by navArgs<CatInfoFragmentArgs>()


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



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}