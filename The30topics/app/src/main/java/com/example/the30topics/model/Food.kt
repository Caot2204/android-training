package com.example.the30topics.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Food(
    @StringRes val tituloRes: Int,
    @DrawableRes val imagenRes: Int,
    @StringRes val descripcionRes: Int
)
