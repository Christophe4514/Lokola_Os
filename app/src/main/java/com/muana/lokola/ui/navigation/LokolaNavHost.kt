package com.muana.lokola.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muana.lokola.data.local.DataSeeder
import com.muana.lokola.ui.launcher.LauncherScreen
import com.muana.lokola.ui.mayebi.LessonDetailScreen
import com.muana.lokola.ui.mayebi.MayebiScreen
import com.muana.lokola.ui.onboarding.OnboardingScreen
import com.muana.lokola.ui.settings.SettingsScreen
import com.muana.lokola.ui.wallpaper.WallpaperPickerScreen
import com.muana.lokola.viewmodel.MainViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

@Composable
fun LokolaNavHost(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val isFirstLaunch by viewModel.isFirstLaunch.collectAsState()

    // Seed initial data on first launch
    LaunchedEffect(Unit) {
        if (isFirstLaunch) {
            viewModel.markOnboardingCompleted()
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (isFirstLaunch) Screen.Onboarding.route else Screen.Launcher.route
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onComplete = {
                    viewModel.markOnboardingCompleted()
                    navController.navigate(Screen.Launcher.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Launcher.route) {
            val currentLanguage by viewModel.currentLanguage.collectAsState()
            
            LauncherScreen(
                onMayebiClick = { navController.navigate(Screen.Mayebi.route) },
                onSettingsClick = { navController.navigate(Screen.Settings.route) },
                currentLanguage = currentLanguage,
                onLanguageChange = { newLanguage ->
                    viewModel.changeLanguage(newLanguage)
                }
            )
        }

        composable(Screen.Mayebi.route) {
            MayebiScreen(
                onLessonClick = { lessonId ->
                    navController.navigate(Screen.LessonDetail.createRoute(lessonId))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.LessonDetail.route,
            arguments = listOf(navArgument("lessonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getInt("lessonId") ?: 0
            LessonDetailScreen(
                lessonId = lessonId,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                onWallpaperClick = { navController.navigate(Screen.WallpaperPicker.route) }
            )
        }

        composable(Screen.WallpaperPicker.route) {
            WallpaperPickerScreen(
                wallpaperManager = viewModel.wallpaperManager,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
