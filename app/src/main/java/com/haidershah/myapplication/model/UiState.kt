package com.haidershah.myapplication.model

data class UiState(
    val colors: List<ColorInfo> = emptyList(),
    val isAutoScrollEnabled: Boolean = false
)
