package com.example.gridcourses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title: Int,
    val courses: Int,
    @DrawableRes val drawableRes: Int
)
