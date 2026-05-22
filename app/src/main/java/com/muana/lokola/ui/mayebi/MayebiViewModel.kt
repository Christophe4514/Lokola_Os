package com.muana.lokola.ui.mayebi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muana.lokola.data.local.DataSeeder
import com.muana.lokola.data.model.Lesson
import com.muana.lokola.domain.usecase.GetLessonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MayebiViewModel @Inject constructor(
    getLessonsUseCase: GetLessonsUseCase,
    private val dataSeeder: DataSeeder
) : ViewModel() {

    val lessons: StateFlow<List<Lesson>> = getLessonsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        // Seed initial data if database is empty
        viewModelScope.launch {
            dataSeeder.seedInitialData()
        }
    }
}
