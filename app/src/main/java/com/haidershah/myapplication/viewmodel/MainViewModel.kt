package com.haidershah.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.haidershah.myapplication.R
import com.haidershah.myapplication.model.ColorInfo
import com.haidershah.myapplication.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                colors = listOf(
                    ColorInfo("Light Purple", "#FFBB86FC", R.color.purple_200),
                    ColorInfo("Orange", "#FFFF9800", R.color.orange),
                    ColorInfo("Teal", "#FF018786", R.color.teal_700),
                    ColorInfo("Red", colorHex = "#FFE57373", R.color.red),
                    ColorInfo("Yellow", colorHex = "#FFFFF176", R.color.yellow),
                )
            )
        }
    }

    fun onAutoScrollClicked() {
        _uiState.update { currentUiState ->
            currentUiState.copy(isAutoScrollEnabled = !currentUiState.isAutoScrollEnabled)

        }
    }
}

