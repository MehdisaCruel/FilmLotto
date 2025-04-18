package com.example.filmlotto

import com.example.filmlotto.destinations.DiceScreenDestination
import com.example.filmlotto.destinations.FavoritesScreenDestination
import com.example.filmlotto.destinations.HomeScreenDestination

import com.example.filmlotto.destinations.*

enum class BottomBarDestination(
    val label: String,
    val direction: DirectionDestination?
) {
    Home("خانه", HomeScreenDestination),
    Dice("قرعه‌کشی", DiceScreenDestination),
    Favorites("علاقه‌مندی‌ها", FavoritesScreenDestination),
    ThemeToggle("تم", null)
}
