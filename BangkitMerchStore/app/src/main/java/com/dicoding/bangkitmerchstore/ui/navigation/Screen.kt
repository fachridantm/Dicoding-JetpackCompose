package com.dicoding.bangkitmerchstore.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailReward : Screen("home/{merchId}") {
        fun createRoute(merchId: Long) = "home/$merchId"
    }
}
