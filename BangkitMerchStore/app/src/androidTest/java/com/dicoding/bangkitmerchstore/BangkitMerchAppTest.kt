package com.dicoding.bangkitmerchstore

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.dicoding.bangkitmerchstore.model.FakeMerchDataSource
import com.dicoding.bangkitmerchstore.ui.navigation.Screen
import com.dicoding.bangkitmerchstore.ui.theme.BangkitMerchStoreTheme
import com.dicoding.bangkitmerchstore.utils.assertCurrentRouteName
import com.dicoding.bangkitmerchstore.utils.onNodeWithStringId

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BangkitMerchAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            BangkitMerchStoreTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                BangkitMerchApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("MerchList").performScrollToIndex(8)
        composeTestRule.onNodeWithText(FakeMerchDataSource.dummyMerch[8].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailMerch.route)
        composeTestRule.onNodeWithText(FakeMerchDataSource.dummyMerch[8].title).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_cart).performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("MerchList").performScrollToIndex(8)
        composeTestRule.onNodeWithText(FakeMerchDataSource.dummyMerch[8].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailMerch.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_checkout_rightBackStack() {
        composeTestRule.onNodeWithText(FakeMerchDataSource.dummyMerch[4].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailMerch.route)
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }
}