package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CupcakeScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeApp(navController = navController)
        }
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen() {
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcakeNavHost_clickNextButton_navigatesToPickupDateScreen() {
        navigateToPickupDateScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    }

    @Test
    fun cupcakeNavHost_clickNextButton_navigatesToSummaryScreen() {
        navigateToSummaryScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Summary.name)
    }

    @Test
    fun cupcakeNavHost_clickBackUpButton_navigatesFromFlavorToStartScreen() {
        navigateToFlavorScreen()
        navigateToUp()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButton_navigatesFromFlavorToStartScreen() {
        navigateToFlavorScreen()
        cancelOrder()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickBackUpButton_navigatesFromPickupToFlavorScreen() {
        navigateToPickupDateScreen()
        navigateToUp()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButton_navigatesFromPickupToStartScreen() {
        navigateToPickupDateScreen()
        cancelOrder()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickBackUpButton_navigatesFromPickupToStartScreen() {
        navigateToPickupDateScreen()
        navigateToUp()
        navigateToUp()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickBackUpButton_navigatesFromSummaryToStartScreen() {
        navigateToSummaryScreen()
        navigateToUp()
        navigateToUp()
        navigateToUp()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButton_navigatesFromSummaryToStartScreen() {
        navigateToSummaryScreen()
        cancelOrder()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    private fun navigateToFlavorScreen() {
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake)
            .performClick()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.chocolate)
            .performClick()
    }

    private fun navigateToPickupDateScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
            .performClick()
    }

    private fun navigateToSummaryScreen() {
        navigateToPickupDateScreen()
        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
            .performClick()
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    private fun navigateToUp() {
        val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText)
            .performClick()
    }

    private fun cancelOrder() {
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel)
            .performClick()
    }

}