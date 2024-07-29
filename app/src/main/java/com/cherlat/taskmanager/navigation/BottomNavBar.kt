package com.cherlat.taskmanager.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cherlat.taskmanager.R
import com.cherlat.taskmanager.ui.theme.AccentViolet
import com.cherlat.taskmanager.ui.theme.BlackIconTint
import com.cherlat.taskmanager.ui.theme.CherlatTaskManagerTheme


@Composable
fun BottomNavBar(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomInsetPadding = with(LocalDensity.current) {
        WindowInsets.navigationBars.getBottom(this).toDp()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {

        Spacer(
            modifier = Modifier
                .padding(top = 13.dp)
                .background(Color.White)
                .matchParentSize()
        )

        Row(
            modifier = modifier then Modifier
                .padding(bottom = bottomInsetPadding)
                .padding(horizontal = 24.dp)
                .align(Alignment.Center)

        ) {
            NavBarIcon(
                iconRes = R.drawable.ic_nav_bar_home,
                selected = currentDestination?.hierarchy?.any { it.route == Screens.Home.name } == true
            ) {
                navController.navigateToSingleScreen(Screens.Home.name)
            }

            Image(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add Task",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .weight(1f)
                    .size(75.dp)
            )

            NavBarIcon(
                iconRes = R.drawable.ic_nav_bar_settings,
                selected = currentDestination?.hierarchy?.any { it.route == Screens.Settings.name } == true
            ) {
                navController.navigateToSingleScreen(Screens.Settings.name)
            }
        }



    }
}

@Composable
private fun RowScope.NavBarIcon(
    @DrawableRes iconRes: Int,
    selected: Boolean,
    onSelected: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(75.dp)
            .height(49.dp)
            .align(Alignment.CenterVertically)
            .clip(CircleShape)
            .clickable { onSelected() }
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = BlackIconTint,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        )

        if (selected) {
            Divider(
                thickness = 4.dp,
                color = AccentViolet,
                modifier = Modifier
                    .width(20.dp)
                    .clip(CircleShape)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

private fun NavHostController.navigateToSingleScreen(route: String) {
    this.navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview
@Composable
private fun BottomNavBarPreview() {
    CherlatTaskManagerTheme {
        BottomNavBar(
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
}
