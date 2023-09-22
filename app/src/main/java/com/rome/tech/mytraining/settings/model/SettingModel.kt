package com.rome.tech.mytraining.settings.model

// clase para la persistencia de datos.
data class SettingModel(
    var volume: Int,
    var darkMode: Boolean,
    var vibration: Boolean,
    var wifi: Boolean
)
