package com.muana.lokola.ui.launcher

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muana.lokola.ui.components.DataSaverWidget
import com.muana.lokola.ui.theme.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

data class AppItem(
    val id: Int,
    val name: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val route: String? = null
)

data class QuickAction(
    val title: String,
    val icon: ImageVector,
    val gradient: List<Color>
)

@Composable
fun LauncherScreen(
    onMayebiClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    var dataSaverEnabled by remember { mutableStateOf(true) }
    
    val currentDate = remember { LocalDate.now() }
    val formattedDate = remember {
        currentDate.format(DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRENCH))
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LauncherBackground)
    ) {
        // Header avec date et salutation
        HeaderSection(formattedDate = formattedDate)
        
        // Data Saver Widget
        DataSaverWidget(
            isEnabled = dataSaverEnabled,
            onToggle = { dataSaverEnabled = it }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Quick Actions (Rumba, Actualités)
        QuickActionsRow()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Grille d'applications
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(getCongoAppList()) { app ->
                CongoAppIcon(
                    app = app,
                    onClick = {
                        when (app.route) {
                            "mayebi" -> onMayebiClick()
                            "settings" -> onSettingsClick()
                        }
                    }
                )
            }
        }
        
        // Dock fixe en bas
        CongoDockBar()
    }
}

@Composable
fun HeaderSection(formattedDate: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CongoBlue
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(CongoBlue, Color(0xFF0056B3))
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = formattedDate.capitalize(),
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        fontWeight = FontWeight.Medium
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Mbote! 🇨🇩",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Bienvenue sur Lokola OS",
                    fontSize = 14.sp,
                    color = Color(0xFFF7D618),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun QuickActionsRow() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(getQuickActions()) { action ->
            QuickActionCard(action = action)
        }
    }
}

@Composable
fun QuickActionCard(action: QuickAction) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(100.dp)
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = action.gradient
                    )
                )
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Icon(
                    imageVector = action.icon,
                    contentDescription = action.title,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = action.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun CongoAppIcon(
    app: AppItem,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(app.backgroundColor)
                .shadow(4.dp, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = app.icon,
                contentDescription = app.name,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
        
        Text(
            text = app.name,
            fontSize = 11.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            color = LauncherTextPrimary,
            modifier = Modifier
                .padding(top = 6.dp)
                .width(64.dp),
            maxLines = 2
        )
    }
}

@Composable
fun CongoDockBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DockItem(Icons.Default.Phone, "Téléphone", CongoGreen)
            DockItem(Icons.Default.Message, "Messages", CongoBlue)
            DockItem(Icons.Default.Public, "Internet", CongoDarkBlue)
            DockItem(Icons.Default.PhotoCamera, "Photo", CongoRed)
        }
    }
}

@Composable
fun DockItem(icon: ImageVector, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(26.dp)
            )
        }
        Text(
            text = label,
            fontSize = 10.sp,
            color = LauncherTextSecondary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

fun getCongoAppList(): List<AppItem> {
    return listOf(
        AppItem(1, "Mayebi", Icons.Default.School, Color(0xFF007FFF), "mayebi"),
        AppItem(2, "Santé", Icons.Default.Favorite, Color(0xFFCE1021), null),
        AppItem(3, "Agri", Icons.Default.Grass, Color(0xFF2E7D32), null),
        AppItem(4, "Météo", Icons.Default.Cloud, Color(0xFF003F87), null),
        AppItem(5, "Photos", Icons.Default.Image, Color(0xFFFF6F00), null),
        AppItem(6, "Musique", Icons.Default.MusicNote, Color(0xFFE91E63), null),
        AppItem(7, "Vidéos", Icons.Default.VideoLibrary, Color(0xFF9C27B0), null),
        AppItem(8, "Fichiers", Icons.Default.Folder, Color(0xFFFF8F00), null),
        AppItem(9, "Calcul", Icons.Default.Calculate, Color(0xFF00897B), null),
        AppItem(10, "Calendrier", Icons.Default.CalendarToday, Color(0xFF5E35B1), null),
        AppItem(11, "Horloge", Icons.Default.AccessTime, Color(0xFF6D4C41), null),
        AppItem(12, "Paramètres", Icons.Default.Settings, Color(0xFF546E7A), "settings")
    )
}

fun getQuickActions(): List<QuickAction> {
    return listOf(
        QuickAction(
            title = "Rumba Congolaise",
            icon = Icons.Default.MusicNote,
            gradient = listOf(Color(0xFFE91E63), Color(0xFFFF5722))
        ),
        QuickAction(
            title = "Actualités RDC",
            icon = Icons.Default.Article,
            gradient = listOf(Color(0xFF007FFF), Color(0xFF003F87))
        )
    )
}
