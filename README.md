# Lokola OS 🇨🇩

**Lokola OS** ("Lokola" = miroir/reflet en Lingala) est un launcher Android personnalisé qui reflète l'identité congolaise.

## Fonctionnalités MVP

- **Launcher Personnalisé**: Écran d'accueil avec grille 4×3 +  dock fixe
- **Multilingue**: Switch Français ↔ Lingala sans redémarrage
- **Data Saver Widget**: Toujours visible pour économiser la data mobile
- **Mayebi (Lokola Edu)**: App éducative hors-ligne avec 3 leçons
- **Onboarding**: 3 écrans de bienvenue
- **Paramètres Lokola**: Configuration Data Saver, langue, infos

## Stack Technique

- **Langage**: Kotlin 100%
- **Min SDK**: 24 (Android 7.0 Nougat)
- **Target SDK**: 33 (Android 13)
- **UI**: Jetpack Compose + Material 3
- **Architecture**: MVVM
- **DI**: Hilt
- **Database**: Room
- **Navigation**: Jetpack Navigation Component

## Structure du Projet

```
app/src/main/java/com/muana/lokola/
├── ui/
│   ├── launcher/          # Écran d'accueil
│   ├── onboarding/        # Onboarding 3 écrans
│   ├── mayebi/            # App éducation
│   ├── settings/          # Paramètres
│   ├── components/        # UI components réutilisables
│   ├── navigation/        # Navigation graph
│   └── theme/             # Thème Material Design
├── data/
│   ├── local/             # Room Database, DAOs
│   ├── repository/        # Data repositories
│   └── model/             # Data classes
├── domain/
│   ├── usecase/           # Business logic
│   └── repository/        # Interfaces
├── di/                    # Hilt modules
├── util/                  # Utilities (LanguageManager, DataSaverManager)
└── viewmodel/             # ViewModels
```

## Installation & Build

### Prérequis
- Android Studio Hedgehog ou plus récent
- JDK 17
- Android SDK Platform 34
- Android SDK Build-Tools 34.0.0

### Build APK

```bash
# Via Android Studio
1. Ouvrir le projet dans Android Studio
2. Sync Gradle files
3. Build → Build Bundle(s) / APK(s) → Build APK(s)

# Via command line
./gradlew assembleDebug
```

### Tester sur émulateur

1. Créer un AVD avec Android 7.0+ (API 24+)
2. Configurer 2-4GB RAM
3. Lancer l'émulateur
4. Installer l'APK: `adb install app/build/outputs/apk/debug/app-debug.apk`
5. Définir Lokola OS comme launcher par défaut

## Roadmap

### v1.0 (MVP - J10) ✅
- [x] Launcher fonctionnel
- [x] Switch langue FR/Lingala
- [x] Widget Data Saver
- [x] Mayebi (3 leçons hors-ligne)
- [x] Onboarding
- [x] Paramètres

### v1.1 (J+30)
- [ ] Swahili, Tshiluba, Kikongo
- [ ] Lokola Santé
- [ ] Widget météo
- [ ] Thème sombre

### v2.0 (J+90)
- [ ] Lokola Agri
- [ ] Synchronisation cloud Wi-Fi
- [ ] OTA updates
- [ ] Support tablettes

## Équipe

**Muana-Tech** - Kinshasa, RDC

## License

Propriétaire - Muana-Tech © 2026

---

**Bosalami na Kinshasa** 🇨🇩
