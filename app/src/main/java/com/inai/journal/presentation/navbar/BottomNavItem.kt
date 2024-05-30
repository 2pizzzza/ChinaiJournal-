package com.inai.journal.presentation.navbar

import com.inai.journal.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    val route: String
) {
    object Home :
        BottomNavItem(
            "Home",
            R.drawable.list_box_outline,
            "home"
        )

    object Scanner :
        BottomNavItem(
            "Scanner",
            R.drawable.qr_code_scanner,
            "scanner"
        )

    object Notification :
        BottomNavItem(
            "Notification",
            R.drawable.bell,
            "notification"
        )

    object Profile :
        BottomNavItem(
            "Profile",
            R.drawable.account,
            "profile"
        )
}