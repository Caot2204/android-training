package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipIUTests {

    @get: Rule
    val composeTestRul = createComposeRule()

    @Test
    fun calculate_20_percent_tip () {
        composeTestRul.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }
        composeTestRul.onNodeWithText("Bill Amount")
            .performTextInput("10")
        composeTestRul.onNodeWithText("Tip percentage")
            .performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRul.onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with this text was found")
    }

}