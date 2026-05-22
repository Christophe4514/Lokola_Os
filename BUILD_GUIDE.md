# Guide de Build - Lokola OS

## Prérequis Système

### Configuration Minimale (votre setup)
- **CPU**: Intel i5 7th Gen
- **RAM**: 8GB
- **OS**: Windows 10/11
- **Espace disque**: 20GB libre minimum

### Logiciels à Installer

1. **Android Studio** (Dernière version stable)
   - Télécharger: https://developer.android.com/studio
   - Installation: Suivre le wizard par défaut
   - Composants requis:
     - Android SDK Platform 34
     - Android SDK Build-Tools 34.0.0
     - Android Emulator
     - Android SDK Platform-Tools

2. **JDK 17** (déjà installé sur votre machine ✓)

---

## Étapes de Build

### Étape 1: Configuration Initiale (1ère fois seulement)

1. **Ouvrir Android Studio**
   ```
   File → Open → Sélectionner le dossier "wotech_launcher"
   ```

2. **Sync Gradle**
   - Android Studio va automatiquement proposer de sync
   - Cliquer sur "Sync Now"
   - Attendre le téléchargement des dépendances (~5-10 min selon connexion)

3. **Configurer local.properties**
   ```bash
   # Copier le template
   copy local.properties.template local.properties

   # Éditer et mettre le bon chemin du SDK
   # Par défaut sur Windows:
   sdk.dir=C\:\\Users\\[VOTRE_NOM]\\AppData\\Local\\Android\\Sdk
   ```

### Étape 2: Build APK Debug

**Option A: Via Android Studio (Recommandé)**
```
1. Menu: Build → Build Bundle(s) / APK(s) → Build APK(s)
2. Attendre la compilation (2-5 min)
3. Popup "APK(s) generated successfully" apparaît
4. Cliquer sur "locate" pour trouver l'APK
```

**Option B: Via Command Line**
```bash
# Dans le dossier racine du projet
gradlew.bat assembleDebug

# L'APK sera généré ici:
# app/build/outputs/apk/debug/app-debug.apk
```

### Étape 3: Créer un Émulateur

1. **Ouvrir Device Manager**
   ```
   Tools → Device Manager
   ```

2. **Créer un nouveau device**
   ```
   Create Device → Phone → Pixel 6
   System Image: API 30 (Android 11.0) ou API 33 (Android 13)
   RAM: 2048 MB (minimum)
   Internal Storage: 2048 MB
   ```

3. **Configurer l'émulateur**
   ```
   - Multi-Core CPU: 2 cores
   - Enable Graphics: Hardware - GLES 2.0
   - Boot option: Cold boot
   ```

4. **Lancer l'émulateur**
   ```
   Cliquer sur le bouton Play ▶️
   Attendre le démarrage (~1-2 min)
   ```

### Étape 4: Installer et Tester

**Option A: Depuis Android Studio**
```
1. Sélectionner l'émulateur dans la dropdown
2. Cliquer sur Run (Shift+F10)
3. L'app s'installe et se lance automatiquement
```

**Option B: Install manuel via ADB**
```bash
# Vérifier que l'émulateur est connecté
adb devices

# Installer l'APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Définir comme launcher par défaut
# (se fait automatiquement au premier lancement)
```

---

## Résolution de Problèmes

### Erreur: "Not enough memory"
```
Solution: Fermer tous les programmes + réduire RAM émulateur à 1536MB
Fichier: gradle.properties déjà configuré avec -Xmx2048m
```

### Erreur: "SDK location not found"
```
Solution: Vérifier local.properties
sdk.dir doit pointer vers le bon chemin
```

### Erreur: "Gradle sync failed"
```
Solution:
1. File → Invalidate Caches → Restart
2. Supprimer dossier .gradle dans le projet
3. Re-sync Gradle
```

### Émulateur trop lent
```
Solution:
1. Activer VT-x dans BIOS (si pas déjà fait)
2. Utiliser HAXM ou WHPX accelerator
3. Réduire résolution écran émulateur
4. Alternative: Utiliser un device physique
```

### Build trop long (>10 min)
```
Normal pour un premier build avec 8GB RAM.
Les builds suivants seront plus rapides grâce au cache.
```

---

## Tests Requis Avant Démo

### Checklist Fonctionnelle

- [ ] **Onboarding**
  - [ ] Écran 1: Bienvenue s'affiche
  - [ ] Écran 2: Data Saver expliqué
  - [ ] Écran 3: Choix langue FR/Lingala
  - [ ] Bouton "Commencer" mène au Launcher

- [ ] **Launcher**
  - [ ] Widget Data Saver visible en haut
  - [ ] Toggle ON/OFF fonctionne
  - [ ] Grille d'apps 4×3 affichée
  - [ ] Dock fixe en bas (4 apps)
  - [ ] Clic sur Mayebi ouvre l'app Edu

- [ ] **Mayebi (Lokola Edu)**
  - [ ] Liste de 3 leçons affichée
  - [ ] Catégories: Math, Français, Sciences
  - [ ] Barre de progression visible
  - [ ] Indicateur "Disponible hors-ligne"
  - [ ] Clic sur une leçon ouvre le détail

- [ ] **Lesson Detail**
  - [ ] Titre de la leçon affiché
  - [ ] Contenu texte lisible
  - [ ] Progression % affichée
  - [ ] Bouton "Commencer à apprendre" fonctionne
  - [ ] Progression augmente à chaque clic (25%, 50%, 75%, 100%)
  - [ ] Message "Leçon terminée!" à 100%

- [ ] **Paramètres**
  - [ ] Toggle Data Saver fonctionne
  - [ ] Sélecteur langue FR ↔ Lingala
  - [ ] Changement de langue appliqué immédiatement
  - [ ] Section "À propos" visible

- [ ] **Multilingue**
  - [ ] Switch FR → Lingala: tous les textes changent
  - [ ] Switch Lingala → FR: retour à la normale
  - [ ] Persistance après redémarrage app

---

## Optimisations pour 8GB RAM

### Pendant le Développement

1. **Fermer tous les programmes inutiles**
   - Navigateur (Chrome consomme beaucoup!)
   - Applications background
   - Autres IDEs

2. **Android Studio Settings**
   ```
   File → Settings → Appearance & Behavior
   - Désactiver animations
   - Réduire font size si nécessaire

   File → Settings → Editor → General
   - Désactiver "Code folding"
   - Désactiver "Live templates"

   File → Settings → Build, Execution, Deployment
   - Compiler → Shared build process heap size: 1024MB
   ```

3. **Émulateur Léger**
   ```
   - Résolution: 720p (pas 1080p)
   - RAM: 1536MB (minimum viable)
   - Pas de Play Store image
   - ABI: x86_64 (plus rapide)
   ```

4. **Build Optimization**
   ```
   Déjà configuré dans gradle.properties:
   - org.gradle.jvmargs=-Xmx2048m
   - org.gradle.parallel=true
   - org.gradle.daemon=true
   ```

---

## Prochaines Étapes Après MVP

1. **Tests Utilisateurs**
   - Installer sur 5 devices physiques différents
   - Collecter feedback
   - Itérer sur UX

2. **Signing APK Release**
   ```bash
   # Générer keystore
   keytool -genkey -v -keystore lokola-release.keystore -alias lokola -keyalg RSA -keysize 2048 -validity 10000

   # Configurer dans build.gradle.kts
   # Build → Generate Signed Bundle / APK
   ```

3. **Distribution**
   - Partager APK via WhatsApp/Telegram
   - Héberger sur GitHub Releases
   - Créer site web simple avec download link

4. **Analytics & Crash Reporting**
   - Intégrer Firebase Crashlytics
   - Track usage avec Firebase Analytics
   - Monitor crashes en temps réel

---

## Support

En cas de problème:
1. Vérifier ce guide section "Résolution de Problèmes"
2. Consulter logs: `adb logcat`
3. Chercher erreur sur StackOverflow
4. Contacter l'équipe technique Muana-Tech

**Bosalami na Kinshasa!** 🇨🇩
