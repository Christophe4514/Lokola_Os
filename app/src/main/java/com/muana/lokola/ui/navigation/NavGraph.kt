package com.muana.lokola.ui.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Launcher : Screen("launcher")
    object Mayebi : Screen("mayebi")
    object LessonDetail : Screen("lesson_detail/{lessonId}") {
        fun createRoute(lessonId: Int) = "lesson_detail/$lessonId"
    }
    object Settings : Screen("settings")
    object WallpaperPicker : Screen("wallpaper_picker")
}
