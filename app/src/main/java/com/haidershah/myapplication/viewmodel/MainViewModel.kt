package com.haidershah.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.haidershah.myapplication.R
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
            ColorInfo("Light Purple", "#FFBB86FC", R.color.purple_200),
            ColorInfo("Orange", "#FFFF9800", R.color.orange),
            ColorInfo("Teal", "#FF018786", R.color.teal_700),
            ColorInfo("Red", colorHex = "#FFE57373", R.color.red),
            ColorInfo("Yellow", colorHex = "#FFFFF176", R.color.yellow),
        )
    }

    fun onAutoScrollClicked() {
        _autoScrollState.value = !_autoScrollState.value
    }
}

