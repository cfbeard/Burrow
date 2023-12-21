package com.badger.burrow

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.badger.burrow.model.BottomNavItem

@Composable
fun BottomNavBar (
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(

    ) {
        items.forEach {item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Column ( horizontalAlignment = Alignment.CenterHorizontally ){
                        if (selected) {
                            Icon(
                                item.selectedIcon,
                                contentDescription = item.name
                            )
                            Text(
                                text = item.name
                            )
                        }
                        else {
                            Icon(
                                item.unselectedIcon,
                                contentDescription = item.name
                            )
                        }
                    }
                }
            )

        }
    }

}