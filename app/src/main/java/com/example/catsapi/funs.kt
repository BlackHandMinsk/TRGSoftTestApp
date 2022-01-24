package com.example.catsapi

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable

import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception


fun placePictureInView(view:ImageView, pictureUrl: String?){
    Glide.with(view.context).load(pictureUrl).into(view)
}

fun setBackGroundAnimation(animationDrawable: AnimationDrawable){
        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(3000)
            start()
        }
    }





