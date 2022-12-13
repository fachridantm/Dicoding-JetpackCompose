package com.dicoding.mycomposetesting.ui.testing

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.dicoding.mycomposetesting.R
import com.dicoding.mycomposetesting.ui.theme.MyComposeTestingTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorAppKtTest {
    @get:Rule
//    val composeTestRule = createComposeRule()
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyComposeTestingTheme {
                CalculatorApp()
            }
        }
    }

    @Test
    fun calculate_area_of_rectangle_correct() {
        with(composeTestRule) {
            /* Using createComposeRule() */
//            onNodeWithText("Masukkan panjang").performTextInput("3")
//            onNodeWithText("Masukkan lebar").performTextInput("4")
//            onNodeWithText("Hitung!").performClick()
//            onNodeWithText("Hasil: 12.0").assertExists()

            /* Using createAndroidComposeRule() */
            onNodeWithText(activity.getString(R.string.enter_length)).performTextInput("3")
            onNodeWithText(activity.getString(R.string.enter_width)).performTextInput("4")
            onNodeWithText(activity.getString(R.string.count)).performClick()
            onNodeWithText(
                activity.getString(R.string.count),
                useUnmergedTree = true
            ).assertHasNoClickAction()
            onNodeWithText(activity.getString(R.string.result, 12.0)).assertExists()
        }
    }

    @Test
    fun wrong_input_not_calculated() {
        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.enter_length)).performTextInput("..3")
            onNodeWithText(activity.getString(R.string.enter_width)).performTextInput("4")
            onNodeWithText(activity.getString(R.string.count)).performClick()
            onNodeWithText(activity.getString(R.string.result, 0.0)).assertExists()
        }
    }
}