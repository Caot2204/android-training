package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcake.R
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SummaryScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val uiState = OrderUiState(
        quantity = 6,
        flavor = "Chocolate",
        date = getFormattedDate(),
        price = "$100"
    )

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    @Test
    fun summaryScreenTest_verifyContent() {
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = uiState,
                onCancelButtonClicked = {  },
                onSendButtonClicked = { example: String, example2: String -> }
            )
        }

        val numberOfCupcakes = composeTestRule.activity.resources.getQuantityString(
            R.plurals.cupcakes,
            uiState.quantity,
            uiState.quantity
        )

        val formattedPrice = composeTestRule.activity.resources.getString(R.string.subtotal_price, uiState.price)

        composeTestRule.onNodeWithText(numberOfCupcakes).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(formattedPrice).assertIsDisplayed()
    }

}