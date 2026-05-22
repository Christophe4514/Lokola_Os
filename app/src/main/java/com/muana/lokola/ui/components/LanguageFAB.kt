package com.muana.lokola.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muana.lokola.R

/**
 * LanguageFAB - Bouton flottant pour switch de langue
 * 
 * Affiche un FAB 🌐 en bas à droite qui ouvre un bottom sheet
 * pour choisir entre Français et Lingala.
 */
@Composable
fun LanguageFAB(
    currentLanguage: String,
    onLanguageChange: (String) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    // FAB Button
    FloatingActionButton(
        onClick = { showBottomSheet = true },
        modifier = Modifier
            .padding(16.dp)
            .shadow(8.dp, CircleShape),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            imageVector = Icons.Default.Language,
            contentDescription = "Changer de langue",
            modifier = Modifier.size(24.dp)
        )
    }

    // Bottom Sheet Modal
    if (showBottomSheet) {
        LanguageBottomSheet(
            currentLanguage = currentLanguage,
            onDismiss = { showBottomSheet = false },
            onLanguageSelect = { language ->
                onLanguageChange(language)
                showBottomSheet = false
            }
        )
    }
}

@Composable
fun LanguageBottomSheet(
    currentLanguage: String,
    onDismiss: () -> Unit,
    onLanguageSelect: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.5f))
            .clickable(onClick = onDismiss)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clickable(enabled = false) {},
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Handle bar
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Title
                Text(
                    text = "Choisir la langue / Pona monɔkɔ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Sélectionnez votre langue préférée",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Language Options
                LanguageOption(
                    flag = "🇫🇷",
                    name = "Français",
                    nativeName = "Français",
                    isSelected = currentLanguage == "fr",
                    onClick = { onLanguageSelect("fr") }
                )

                Spacer(modifier = Modifier.height(12.dp))

                LanguageOption(
                    flag = "🇨🇩",
                    name = "Lingala",
                    nativeName = "Lingála",
                    isSelected = currentLanguage == "ling",
                    onClick = { onLanguageSelect("ling") }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Cancel button
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.common_cancel),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun LanguageOption(
    flag: String,
    name: String,
    nativeName: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        ),
        border = if (isSelected)
            androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        else
            androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = flag,
                    fontSize = 32.sp
                )

                Column {
                    Text(
                        text = name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = nativeName,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Sélectionné",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
