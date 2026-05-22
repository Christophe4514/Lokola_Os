package com.muana.lokola.ui.theme

import androidx.compose.ui.graphics.Color

// Couleurs inspirées du drapeau congolais et de la culture locale
val CongoBlue = Color(0xFF007FFF)       // Bleu du drapeau
val CongoRed = Color(0xFFCE1021)        // Rouge du drapeau  
val CongoYellow = Color(0xFFF7D618)     // Jaune/Or du drapeau
val CongoGreen = Color(0xFF2E7D32)      // Vert forêt tropicale
val CongoDarkBlue = Color(0xFF003F87)   // Bleu profond
val CongoEarth = Color(0xFF8D6E63)      // Terre ocre

// Couleurs pour le launcher
val LauncherBackground = Color(0xFFF8F9FA)
val LauncherSurface = Color(0xFFFFFFFF)
val LauncherCardPrimary = Color(0xFF007FFF)
val LauncherCardSecondary = Color(0xFFF7D618)
val LauncherTextPrimary = Color(0xFF1A1A2E)
val LauncherTextSecondary = Color(0xFF4A4A68)

// Couleurs pour les widgets
val WidgetGradientStart = Color(0xFF007FFF)
val WidgetGradientEnd = Color(0xFFCE1021)

// Material 3 color scheme - Thème Congo
val LightColorScheme = androidx.compose.material3.lightColorScheme(
    primary = CongoBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD4E6FF),
    onPrimaryContainer = Color(0xFF001B3D),
    
    secondary = CongoYellow,
    onSecondary = Color(0xFF1A1A1A),
    secondaryContainer = Color(0xFFFFF4CC),
    onSecondaryContainer = Color(0xFF3D3300),
    
    tertiary = CongoRed,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFDAD6),
    onTertiaryContainer = Color(0xFF410002),
    
    background = LauncherBackground,
    onBackground = LauncherTextPrimary,
    
    surface = LauncherSurface,
    onSurface = LauncherTextPrimary,
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
    
    error = CongoRed,
    onError = Color.White,
    
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFC4C7C5)
)
