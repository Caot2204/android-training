package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeStartScreen {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun startScreen_verifyContent() {
        val options = listOf(
            Pair(com.example.cupcake.R.string.one_cupcake, 1),
            Pair(com.example.cupcake.R.string.six_cupcakes, 6),
            Pair(com.example.cupcake.R.string.twelve_cupcakes, 12)
        )

        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = options,
                onNextButtonClicked = {  }
            )
        }

        options.forEach { option ->
            composeTestRule.onNodeWithText(
                composeTestRule.activity.getString(option.first)
            ).assertIsDisplayed()
        }
    }

}