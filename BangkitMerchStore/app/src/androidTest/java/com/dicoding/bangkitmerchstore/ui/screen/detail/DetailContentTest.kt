package com.dicoding.bangkitmerchstore.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.dicoding.bangkitmerchstore.R
import com.dicoding.bangkitmerchstore.model.Merch
import com.dicoding.bangkitmerchstore.model.OrderMerch
import com.dicoding.bangkitmerchstore.utils.onNodeWithStringId
import com.dicoding.bangkitmerchstore.ui.theme.BangkitMerchStoreTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailContentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrdeMerch = OrderMerch(
        merch =  Merch(
            4,
            R.drawable.bangkit_tumblr,
            "Digital Tumblr",
            "Digital tumblr bangkit berwarna merah dengan stiker bangkit berwarna putih dan terdapat suhu temperature check di bagian tutup tumblr",
            1500
        ),
        count = 0
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            BangkitMerchStoreTheme {
                DetailContent(
                    fakeOrdeMerch.merch.image,
                    fakeOrdeMerch.merch.title,
                    fakeOrdeMerch.merch.desc,
                    fakeOrdeMerch.merch.requiredPoint,
                    fakeOrdeMerch.count,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeOrdeMerch.merch.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.required_point,
                fakeOrdeMerch.merch.requiredPoint
            )
        ).assertIsDisplayed()
    }

    @Test
    fun increaseProduct_buttonEnabled() {
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsNotEnabled()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsEnabled()
    }

    @Test
    fun increaseProduct_correctCounter() {
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick().performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))
    }

    // Negative case
    @Test
    fun decreaseProduct_stillZero() {
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("0"))
    }
}