package com.example.dessertclicker.ui

import com.example.dessertclicker.R

data class DessertUiState(
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertImage: Int = R.drawable.cupcake,
    val currentPrice: Int = 5,
    val currentProduction: Int = 0
)
