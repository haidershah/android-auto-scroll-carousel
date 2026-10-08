package com.haidershah.myapplication

import androidx.lifecycle.ViewModel
import com.haidershah.myapplication.model.ColorInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
// todo uistate

    private val _colorsState = MutableStateFlow(emptyList<ColorInfo>())
    val colorsState = _colorsState.asStateFlow()

    private val _autoScrollState = MutableStateFlow(false)
    val autoScrollState = _autoScrollState.asStateFlow()

    init {
        _colorsState.value = listOf(
            ColorInfo("Pink", "#FFBB86FC", R.color.purple_200),
            ColorInfo("Blue", "#FF018786", R.color.purple_700),
            ColorInfo("Green", "#FF018786", R.color.teal_700),
            ColorInfo("Red", colorHex = "#FF0000", R.color.red),
            ColorInfo("Yellow", colorHex = "#FFFF00", R.color.yellow),
        )
    }

    fun onAutoScrollClicked() {
        _autoScrollState.value = !_autoScrollState.value
    }
}

