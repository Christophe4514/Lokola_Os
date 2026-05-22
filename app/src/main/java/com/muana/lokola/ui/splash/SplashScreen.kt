package com.muana.lokola.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinish: () -> Unit
) {
    var currentPhase by remember { mutableStateOf(SplashPhase.BootUp) }
    
    LaunchedEffect(Unit) {
        // Simulation du boot up
        delay(1500)
        currentPhase = SplashPhase.Logo
        delay(2000)
        currentPhase = SplashPhase.ShuttingDown
        delay(1000)
        onFinish()
    }
    
    when (currentPhase) {
        SplashPhase.BootUp -> BootUpAnimation()
        SplashPhase.Logo -> LogoAnimation()
        SplashPhase.ShuttingDown -> ShutDownAnimation()
    }
}

enum class SplashPhase {
    BootUp,
    Logo,
    ShuttingDown
}

@Composable
fun BootUpAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "boot")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOut),
            repeatMode = RepeatMode.Restart
        ),
        label = "progress"
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Logo Lokola
            Text(
                text = "LOKOLA",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF007FFF),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "OS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFF7D618),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Barre de progression
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color(0xFF333333))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFF007FFF), Color(0xFFF7D618))
                            )
                        )
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Mbote! Chargement...",
                fontSize = 14.sp,
                color = Color(0xFFCCCCCC),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun LogoAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF003F87), Color(0xFF007FFF))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    alpha = alpha
                )
        ) {
            // Cercle décoratif
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(Color(0xFFF7D618)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🇨🇩",
                    fontSize = 64.sp
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "LOKOLA OS",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Oyo ezali Congo!",
                fontSize = 16.sp,
                color = Color(0xFFF7D618),
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Petit texte animé
            Text(
                text = "Made in Kinshasa ❤️",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun ShutDownAnimation() {
    val animateAlpha = remember { Animatable(1f) }
    
    LaunchedEffect(Unit) {
        animateAlpha.animateTo(
            targetValue = 0f,
            animationSpec = tween(1000, easing = EaseOut)
        )
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = animateAlpha.value)),
        contentAlignment = Alignment.Center
    ) {
        if (animateAlpha.value > 0.1f) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🌙",
                    fontSize = 48.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "À bientôt!",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
