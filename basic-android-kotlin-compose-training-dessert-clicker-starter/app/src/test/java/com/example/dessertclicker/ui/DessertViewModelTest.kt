package com.example.dessertclicker.ui.test

import com.example.dessertclicker.ui.DessertViewModel
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.testng.Assert.assertNotEquals

class DessertViewModelTest {

    private val dessertViewModel = DessertViewModel()

    @Test
    fun dessertViewModel_touchDessert_revenueAndDessertsSoldUpdated() {
        dessertViewModel.updateRevenueAndDessertsSold()
        dessertViewModel.determineDessertToShow()

        val uiState = dessertViewModel.uiState.value
        val currentPrice = uiState.currentPrice
        val currentProduction = uiState.currentProduction

        assertEquals(1, uiState.dessertsSold)
        assertEquals(5, uiState.revenue)
        assertEquals(10, currentPrice)
        assertEquals(5, currentProduction)
    }

}