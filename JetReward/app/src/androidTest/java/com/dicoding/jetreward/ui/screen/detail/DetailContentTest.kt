package com.dicoding.jetreward.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.dicoding.jetreward.R
import com.dicoding.jetreward.model.OrderReward
import com.dicoding.jetreward.model.Reward
import com.dicoding.jetreward.onNodeWithStringId
import com.dicoding.jetreward.ui.theme.JetRewardTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailContentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderReward = OrderReward(
        reward = Reward(
            id = 4,
            title = "Jaket Hoodie Dicoding",
            image = R.drawable.reward_4,
            requiredPoint = 7500
        ),
        count = 0
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetRewardTheme {
                DetailContent(
                    image = fakeOrderReward.reward.image,
                    title = fakeOrderReward.reward.title,
                    basePoint = fakeOrderReward.reward.requiredPoint,
                    count = fakeOrderReward.count,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.apply {
            onNodeWithText(fakeOrderReward.reward.title).assertIsDisplayed()
            onNodeWithText(
                activity.getString(R.string.required_point,
                fakeOrderReward.reward.requiredPoint
                )
            ).assertIsDisplayed()
        }
    }

    @Test
    fun increaseProduct_buttonEnabled() {
        with(composeTestRule) {
            onNodeWithContentDescription("Order Button").assertIsNotEnabled()
            onNodeWithStringId(R.string.plus_symbol).performClick()
            onNodeWithContentDescription("Order Button").assertIsEnabled()
        }
    }
}