# 🚀 Guide de Build - Lokola OS

## ⚠️ Situation Actuelle

Le projet Lokola OS est **100% codé et prêt à builder**, mais Gradle n'est pas installé sur votre système.

---

## ✅ Option 1: Build via Android Studio (RECOMMANDÉ)

### Étape 1: Ouvrir le Projet

1. **Lancer Android Studio**
2. **File → Open**
3. **Sélectionner le dossier:** `d:\Nouveau dossier\ppc\wotech_launcher`
4. **Cliquer "OK"**

### Étape 2: Sync Gradle

1. Android Studio détecte automatiquement le projet Gradle
2. **Popup apparaît:** "Gradle project found. Sync now?"
3. **Cliquer sur "Sync Now"**
4. **Attendre 5-10 minutes** (premier téléchargement des dépendances ~500MB)

**Progress bar en bas:**
```
Syncing project...
Downloading Gradle distribution...
Resolving dependencies...
Indexing files...
```

### Étape 3: Vérifier Configuration

Après sync, vérifier dans **File → Project Structure**:

```
SDK Location:
  ✓ Android SDK: C:\Users\[VOTRE_NOM]\AppData\Local\Android\Sdk
  
Default Settings:
  ✓ Gradle JDK: JDK 17
  ✓ Android Gradle Plugin Version: 8.2.0
  ✓ Gradle Version: 8.2
```

### Étape 4: Build APK Debug

**Menu principal:**
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

**Ou raccourci clavier:**
```
Ctrl + Shift + F9
```

**Progress:**
```
Running Gradle task 'assembleDebug'...
Compiling Kotlin sources...
Merging resources...
Packaging APK...
```

**Durée:** 2-5 minutes (premier build), 1-2 min (builds suivants)

### Étape 5: APK Généré

**Popup apparaît:**
```
✓ APK(s) generated successfully:
  d:\Nouveau dossier\ppc\wotech_launcher\app\build\outputs\apk\debug\app-debug.apk
  
[locate] [analyze]
```

**Cliquer sur "locate"** pour ouvrir l'explorateur.

---

## ✅ Option 2: Installer Gradle Manuellement

Si vous préférez builder en ligne de commande:

### Installation Gradle (Windows)

1. **Télécharger Gradle 8.2:**
   ```
   https://gradle.org/releases/
   → gradle-8.2-bin.zip
   ```

2. **Extraire:**
   ```
   C:\Program Files\Gradle\gradle-8.2
   ```

3. **Ajouter au PATH:**
   ```powershell
   # PowerShell (Admin)
   [Environment]::SetEnvironmentVariable(
       "Path", 
       $env:Path + ";C:\Program Files\Gradle\gradle-8.2\bin", 
       "Machine"
   )
   ```

4. **Vérifier:**
   ```bash
   gradle --version
   # Doit afficher: Gradle 8.2
   ```

### Build avec Gradle

```bash
cd "d:\Nouveau dossier\ppc\wotech_launcher"
gradle assembleDebug

# APK généré:
# app/build/outputs/apk/debug/app-debug.apk
```

---

## 📱 Tester sur Émulateur

### Créer un AVD (Android Virtual Device)

1. **Ouvrir Device Manager:**
   ```
   Tools → Device Manager
   ```

2. **Créer Device:**
   ```
   Create Device → Phone → Pixel 6
   ```

3. **Système Image:**
   ```
   API Level: 33 (Android 13) ou 30 (Android 11)
   ABI: x86_64 (plus rapide)
   Download si nécessaire (~2GB)
   ```

4. **Configuration:**
   ```
   RAM: 2048 MB
   VM Heap: 256 MB
   Internal Storage: 2048 MB
   SD Card: None
   ```

5. **Finish**

### Lancer Émulateur

```
Device Manager → Pixel 6 → Play Button ▶️
```

**Premier démarrage:** 1-2 minutes

### Installer APK

**Option A: Depuis Android Studio**
```
Run → Run 'app' (Shift+F10)
Sélectionner émulateur dans la liste
APK s'installe automatiquement
```

**Option B: Via ADB**
```bash
# Vérifier connexion
adb devices
# Doit afficher: emulator-5554    device

# Installer
adb install app/build/outputs/apk/debug/app-debug.apk

# Output attendu:
# Performing Streamed Install
# Success
```

### Définir comme Launcher par Défaut

1. **Sur l'émulateur:**
   ```
   Settings → Apps → Default apps → Home app
   ```

2. **Sélectionner:**
   ```
   ☑ Lokola OS
   ```

3. **Appuyer bouton Home:**
   ```
   Lokola OS devrait apparaître! 🎉
   ```

---

## 🧪 Checklist Tests

### Tests Techniques
- [ ] Build Gradle réussi sans erreurs
- [ ] APK généré: `app-debug.apk` (taille ~8-12 MB)
- [ ] 0 crash au lancement

### Tests Fonctionnels
- [ ] Lokola OS apparaît dans "Choisir l'appli Accueil"
- [ ] Écran d'accueil s'affiche avec grille 4×3
- [ ] Header affiche date + "Mbote! 🇨🇩"
- [ ] Data Saver Widget visible
- [ ] Dock fixe en bas (Téléphone, Messages, Internet, Photo)

### Tests Interactions
- [ ] **Tap Téléphone → Composeur s'ouvre** ✨
- [ ] **Tap Messages → App SMS s'ouvre** ✨
- [ ] **Tap Internet → Browser s'ouvre** ✨
- [ ] **Tap Photo → Caméra s'ouvre** ✨
- [ ] Tap Mayebi → Écran liste leçons
- [ ] Tap Paramètres → Écran settings

### Test LanguageFAB
- [ ] **FAB 🌐 visible en bas à droite** ✨
- [ ] **Clic FAB → Bottom sheet s'ouvre** ✨
- [ ] Options FR + Lingala affichées
- [ ] Bouton Annuler ferme le sheet

---

## 🚨 Résolution de Problèmes

### Erreur: "SDK location not found"

**Solution:**
1. Créer fichier `local.properties` à la racine:
   ```properties
   sdk.dir=C\:\\Users\\[VOTRE_NOM]\\AppData\\Local\\Android\\Sdk
   ```

2. Remplacer `[VOTRE_NOM]` par votre nom d'utilisateur Windows

### Erreur: "Not enough memory"

**Solution:**
1. Fermer Chrome/autres programmes
2. Dans `gradle.properties`:
   ```properties
   org.gradle.jvmargs=-Xmx2048m
   ```
   Déjà configuré ✅

### Erreur: "Build failed - Compilation error"

**Solution:**
1. Lire le message d'erreur complet
2. Vérifier ligne indiquée
3. Copier erreur → demander à Lingma:
   ```
   "Cette erreur apparaît: [coller erreur]. Corrige le code."
   ```

### Émulateur Trop Lent

**Solution:**
1. Activer VT-x dans BIOS
2. Réduire résolution écran: 720p au lieu de 1080p
3. RAM émulateur: 1536 MB minimum
4. Utiliser HAXM accelerator (Intel)

### APK Ne S'installe Pas

**Solution:**
```bash
# Désinstaller version précédente
adb uninstall com.muana.lokola

# Réinstaller
adb install app/build/outputs/apk/debug/app-debug.apk
```

---

## 📊 Performance Attendue

| Métrique | Valeur Cible |
|----------|--------------|
| **Taille APK debug** | 8-12 MB |
| **Taille APK release** | 5-8 MB (avec ProGuard) |
| **Cold start** | < 3 secondes |
| **RAM usage** | 150-250 MB |
| **FPS scroll** | ~60 FPS |

---

## 🎯 Prochaines Étapes Après Build

Une fois le build réussi et tests validés:

### Sprint 3 (J6-7): Multilingue + Edu
1. **Switch langue FR ↔ Lingala 100% fonctionnel**
   - Tous les textes UI changent dynamiquement
   - Persistance après redémarrage

2. **Lokola Edu finalisé**
   - 3 leçons affichées avec contenu réel
   - Progression sauvegardée (Room DB)
   - Tests utilisateurs Kinshasa

3. **Optimisations**
   - Réduire taille APK
   - Améliorer performance scroll
   - Ajouter animations douces

---

## 💡 Tips pour 8GB RAM

### Pendant Build
1. **Fermer programmes inutiles:**
   - Chrome (consomme 2-4GB!)
   - VS Code, autres IDEs
   - Applications background

2. **Android Studio Settings:**
   ```
   File → Settings → Appearance & Behavior
   → Désactiver animations
   → Réduire font size
   ```

3. **Build en ligne de commande** (plus léger):
   ```bash
   ./gradlew assembleDebug --no-daemon
   ```

### Pendant Tests Émulateur
1. **Réduire RAM émulateur:** 1536-2048 MB
2. **Pas de Play Store image** (plus léger)
3. **Résolution 720p** au lieu de 1080p
4. **Fermer Android Studio** si juste tester APK

---

## 🏆 Une fois Build Réussi

**Vous aurez:**
- ✅ APK installable sur émulateur/device
- ✅ Launcher fonctionnel avec design Congo
- ✅ Navigation apps système opérationnelle
- ✅ Base solide pour Sprint 3

**Share APK:**
```
Copier app-debug.apk sur clé USB
→ Installer sur devices physiques à Kinshasa
→ Tests utilisateurs réels!
```

---

*Bosalami na Kinshasa! Oyo ezali Congo!* 🇨🇩

*Développé avec ❤️ par Muana-Tech*
