package com.muana.lokola.data.local

import com.muana.lokola.data.model.Lesson
import com.muana.lokola.data.model.LessonCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSeeder @Inject constructor(
    private val lessonDao: LessonDao
) {
    suspend fun seedInitialData() {
        // Check if data already exists
        val existingLessons = lessonDao.getAllLessons()
        // Note: This is a Flow, so we can't check it directly here
        // In production, you'd use a different approach

        val initialLessons = listOf(
            Lesson(
                id = 1,
                title = "Les nombres de 1 à 100",
                category = LessonCategory.MATH,
                content = """
                    # Les nombres de 1 à 100

                    ## Introduction
                    Apprenons à compter de 1 à 100 en français.

                    ## Les nombres de base
                    - 1: Un
                    - 2: Deux
                    - 3: Trois
                    - 4: Quatre
                    - 5: Cinq
                    - 6: Six
                    - 7: Sept
                    - 8: Huit
                    - 9: Neuf
                    - 10: Dix

                    ## Les dizaines
                    - 10: Dix
                    - 20: Vingt
                    - 30: Trente
                    - 40: Quarante
                    - 50: Cinquante
                    - 60: Soixante
                    - 70: Soixante-dix
                    - 80: Quatre-vingts
                    - 90: Quatre-vingt-dix
                    - 100: Cent

                    ## Exercice
                    Essayez de compter de 1 à 20 sans regarder!
                """.trimIndent(),
                imageUrl = null,
                progress = 0,
                isCompleted = false,
                isDownloaded = true
            ),
            Lesson(
                id = 2,
                title = "L'alphabet français",
                category = LessonCategory.FRENCH,
                content = """
                    # L'alphabet français

                    ## Les 26 lettres
                    L'alphabet français contient 26 lettres:

                    ### Voyelles (6)
                    A, E, I, O, U, Y

                    ### Consonnes (20)
                    B, C, D, F, G, H, J, K, L, M, N, P, Q, R, S, T, V, W, X, Z

                    ## Alphabet complet
                    A B C D E F G H I J K L M N O P Q R S T U V W X Y Z

                    ## Prononciation
                    - A: [a] comme dans "papa"
                    - E: [ə] comme dans "le"
                    - I: [i] comme dans "ici"
                    - O: [o] comme dans "eau"
                    - U: [y] comme dans "tu"

                    ## Exercice
                    Épelez votre nom en français!
                """.trimIndent(),
                imageUrl = null,
                progress = 0,
                isCompleted = false,
                isDownloaded = true
            ),
            Lesson(
                id = 3,
                title = "Le système solaire",
                category = LessonCategory.SCIENCE,
                content = """
                    # Le système solaire

                    ## Introduction
                    Le système solaire est composé du Soleil et de tous les objets qui tournent autour de lui.

                    ## Le Soleil
                    Le Soleil est une étoile au centre de notre système solaire. Il donne lumière et chaleur à la Terre.

                    ## Les planètes (8)
                    Dans l'ordre, en partant du Soleil:

                    1. **Mercure**: La plus proche du Soleil
                    2. **Vénus**: La plus chaude
                    3. **Terre**: Notre planète (la seule avec la vie!)
                    4. **Mars**: La planète rouge
                    5. **Jupiter**: La plus grande
                    6. **Saturne**: Connue pour ses anneaux
                    7. **Uranus**: Tourne sur le côté
                    8. **Neptune**: La plus éloignée

                    ## La Terre
                    - 3ème planète du Soleil
                    - Seule planète connue avec de la vie
                    - A un satellite: la Lune
                    - 71% d'eau

                    ## Fun fact
                    Jupiter est si grande que 1300 Terres pourraient rentrer dedans!
                """.trimIndent(),
                imageUrl = null,
                progress = 0,
                isCompleted = false,
                isDownloaded = true
            )
        )

        lessonDao.insertLessons(initialLessons)
    }
}
