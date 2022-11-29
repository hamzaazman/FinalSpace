package com.hamzaazman.finalspace.util

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable


fun circularProgressLoading(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 30f
        start()
    }
}