package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.ui.SelectOptionScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    lateinit var flavors: List<String>
    lateinit var subtotal: String

    @Test
    fun selectOptionScreen_verifyContent() {
        setContentScreen()
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                com.example.cupcake.R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).assertIsNotEnabled()
    }

    @Test
    fun selectOptionScreen_chooseFlavor_nextButtonIsEnabled() {
        setContentScreen()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.chocolate)
            .performClick()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).assertIsEnabled()
    }

    private fun setContentScreen() {
        flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(
                subtotal = subtotal,
                options = flavors,
                onCancelButtonClicked = {  },
                onNextButtonClicked = {  }
            )
        }
    }
}