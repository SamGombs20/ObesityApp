package com.josh.obesityapp.navigation

import com.josh.obesityapp.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String){
    data object Home:NavigationItem("home", R.drawable.home, "Home")
    data object Blog:NavigationItem("blog", R.drawable.blog, "com.josh.obesityapp.data.model.Blog")
}
sealed class NestedNavigationItem(var route: String){
    data object CollectData:NestedNavigationItem("home_collect_data")
    data object PersonalInfo:NestedNavigationItem("home_personal_info")
    data object EatingInfo:NestedNavigationItem("home_eating_info")
    data object DailyInfo:NestedNavigationItem("home_daily_info")
    data object TransportInfo:NestedNavigationItem("home_transport_info")
    data object Home:NestedNavigationItem("home_home_main")
}