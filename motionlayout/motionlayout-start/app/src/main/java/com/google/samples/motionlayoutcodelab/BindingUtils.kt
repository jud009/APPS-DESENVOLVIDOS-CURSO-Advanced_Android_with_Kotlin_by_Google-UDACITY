package com.google.samples.motionlayoutcodelab

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

//kotlin plugin is needed
@BindingAdapter("setBackgroundGlide")
fun ImageView.setBackgroundGlide(imageRes: Int){
    Glide.with(this.context).load(imageRes).into(this)
}

