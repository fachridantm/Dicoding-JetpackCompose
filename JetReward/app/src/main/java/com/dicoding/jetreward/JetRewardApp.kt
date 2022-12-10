package com.dicoding.jetreward

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dicoding.jetreward.ui.navigation.NavigationItem
import com.dicoding.jetreward.ui.navigation.Screen
import com.dicoding.jetreward.ui.screen.cart.CartScreen
import com.dicoding.jetreward.ui.screen.home.HomeScreen
import com.dicoding.jetreward.ui.screen.profile.ProfileScreen
import com.dicoding.jetreward.ui.theme.JetRewardTheme

@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = { BottomBar(navController) },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Cart.route) { CartScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = modifier,
    ) {
        val navigationItem = listOf(
            NavigationItem(title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home),
            NavigationItem(title = stringResource(R.string.menu_cart),
                icon = Icons.Filled.ShoppingCart,
                screen = Screen.Cart),
            NavigationItem(title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile),
        )
        BottomNavigation {
            navigationItem.map { item ->
                BottomNavigationItem(
                    icon = { Icon(item.icon, contentDescription = item.title) },
                    label = { Text(text = item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JetHeroesAppPreview() {
    JetRewardTheme {
        JetRewardApp()
    }
}
