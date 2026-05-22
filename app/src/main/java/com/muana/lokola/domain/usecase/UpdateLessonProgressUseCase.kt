package com.muana.lokola.domain.usecase

import com.muana.lokola.data.repository.LessonRepository
import javax.inject.Inject

class UpdateLessonProgressUseCase @Inject constructor(
    private val repository: LessonRepository
) {
    suspend operator fun invoke(lessonId: Int, progress: Int, isCompleted: Boolean) {
        repository.updateLessonProgress(lessonId, progress, isCompleted)
    }
}
