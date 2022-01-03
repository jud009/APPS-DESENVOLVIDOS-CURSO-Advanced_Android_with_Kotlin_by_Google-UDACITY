package com.google.samples.motionlayoutcodelab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.samples.motionlayoutcodelab.databinding.ActivityStep5Binding

class Step5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityStep5Binding>(this, R.layout.activity_step5)
    }

}
