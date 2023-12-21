package com.badger.burrow

import android.content.res.Resources
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.badger.burrow.common.snackbar.SnackbarManager
import com.badger.burrow.model.BottomNavItem
import com.badger.burrow.screens.home.HomeScreen
import com.badger.burrow.ui.theme.BurrowTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun BurrowApp() {
    BurrowTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            val appState = rememberAppState()
            Scaffold (
                snackbarHost = {
                    SnackbarHost (
                        hostState = appState.snackbarHostState,
                        snackbar = {snackbarData ->
                            Snackbar(
                                snackbarData = snackbarData,
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                },
                bottomBar = {
                    BottomNavBar(
                        items = navItems,
                        navController = appState.navController,
                        onItemClick = { appState.navigate(it.route) } // FIXME: TEST
                    )
                }

            ) { innerPadding ->
                NavHost(
                    navController = appState.navController,
                    startDestination = HOME_SCREEN,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    burrowGraph(appState)
                }
            }
        }
    }
}
@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(navController, snackbarManager, resources, coroutineScope) {
        BurrowAppState(navController, snackbarHostState, snackbarManager, resources, coroutineScope)
    }

fun NavGraphBuilder.burrowGraph(appState: BurrowAppState) {
    composable(HOME_SCREEN) {
        HomeScreen(openScreen = { route -> appState.navigate(route) })
    }
}

private val navItems = listOf(
    BottomNavItem(
        name = "Home",
        route = HOME_SCREEN,
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    ),
    BottomNavItem(
        name = "Tasks",
        route = TASKS_SCREEN,
        unselectedIcon = Icons.Outlined.Build,
        selectedIcon = Icons.Filled.Build
    ),
    BottomNavItem(
        name = "Groceries",
        route = GROCERIES_SCREEN,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart
    )
)