package com.example.tiptime

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTest {

    @Test
    fun calculateTip_20PercenteNoRoundUp() {
        val amount = 10.00
        val percent = 20.00
        var expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(false, amount, percent)
        assertEquals(expectedTip, actualTip)
    }

}