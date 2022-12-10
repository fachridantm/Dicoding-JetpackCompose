package com.dicoding.jetreward

import android.graphics.drawable.Icon
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.jetreward.ui.navigation.NavigationItem
import com.dicoding.jetreward.ui.navigation.Screen
import com.dicoding.jetreward.ui.theme.JetRewardTheme

@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier,
) {

}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        modifier = modifier,
    ) {
        val navigationItem = listOf(
            NavigationItem(title = stringResource(id = R.string.menu_home),
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
                BottomNavigationItem(icon = { Icon(item.icon, contentDescription = item.title) },
                    label = { Text(text = item.title) },
                    selected = true,
                    onClick = { })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    JetRewardTheme {
        JetRewardApp()
    }
}
