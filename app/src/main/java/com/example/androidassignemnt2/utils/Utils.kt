package com.example.androidassignemnt2.utils

import android.view.animation.Animation
import android.widget.TextView

object Utils {

    fun TextView.shakeView(shakeAnimation: Animation) {
        startAnimation(shakeAnimation)
        requestFocus()
    }

}