package com.josh.obesityapp.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.josh.obesityapp.navigation.NavigationItem
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen
import com.josh.obesityapp.ui.theme.customLightBrown
import com.josh.obesityapp.ui.theme.customLightGreen
import com.josh.obesityapp.ui.theme.customSeaGreen

@Composable
fun BottomNavbar(navController: NavController){
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Blog
    )
    NavigationBar(
        modifier = Modifier.height(110 .dp)
            .clip(RoundedCornerShape(topStart = 16 .dp, topEnd = 16 .dp)),
        containerColor = customLightBrown
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { navItem->
            val isSelected = currentRoute?.startsWith(navItem.route) ==true
            val selectedColor = customBrown
            val unselectedColor = customDarkGreen

            // Animate icon size
            val iconScale by animateFloatAsState(
                targetValue = if (isSelected) 1.2f else 1f, // Increase size when selected
                animationSpec = tween(durationMillis = 300)
            )
            // Animate icon rotation
            val iconRotation by animateFloatAsState(
                targetValue = if (isSelected) 360f else 0f, // Rotate when selected
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )

            // Animate text opacity (fade in/out)
            val textAlpha by animateFloatAsState(
                targetValue = if (isSelected) 1f else 0.7f, // Make text more visible when selected
                animationSpec = tween(durationMillis = 300)
            )

            // Animate color change
            val iconColor by animateColorAsState(
                targetValue = if (isSelected) selectedColor else unselectedColor,
                animationSpec = tween(durationMillis = 300)
            )

            val textColor by animateColorAsState(
                targetValue = if (isSelected) selectedColor else unselectedColor,
                animationSpec = tween(durationMillis = 300)
            )
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(navItem.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                } },
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = navItem.title,
                        modifier = Modifier.size(20 .dp).scale(iconScale).rotate(iconRotation),
                        tint = iconColor
                    )
                },
                label = {
                    Text(
                        text = navItem.title,
                        color = textColor.copy(alpha = textAlpha)
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = customBrown,
                    selectedTextColor = customBrown,
                    unselectedIconColor = customDarkGreen,
                    unselectedTextColor = customDarkGreen,
                    selectedIndicatorColor = customLightGreen,
                    disabledIconColor = Color.Gray,
                    disabledTextColor = Color.Gray
                )
            )
        }
    }
}