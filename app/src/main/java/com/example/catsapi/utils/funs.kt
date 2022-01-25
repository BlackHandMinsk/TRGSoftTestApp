package com.example.catsapi.utils


import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.example.catsapi.R


fun placePictureInView(view: ImageView, pictureUrl: String?) {
    Glide.with(view.context).load(pictureUrl).placeholder(R.drawable.placeholder).into(view)
}

fun setBackGroundAnimation(animationDrawable: AnimationDrawable) {
    animationDrawable.apply {
        setEnterFadeDuration(1000)
        setExitFadeDuration(3000)
        start()
    }
}





