package com.rome.tech.mytraining.settings

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rome.tech.mytraining.databinding.ActivitySettingsBinding
import com.rome.tech.mytraining.settings.model.SettingModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


/* TODO reubicar el metodo de extension que defino aqui para la persistencia
    settings pasaria a ser la base de datos del DataStore y es un Singleton */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class SettingsActivity : AppCompatActivity() {
    companion object {
        const val KEY_VOLUME_LVL = "key_volume_lvl"
        const val KEY_VIBRATION = "key_vibration"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_DARK_MODE = "key_dark_mode"
    }

    private lateinit var binding: ActivitySettingsBinding

    // bandera para manejar excepcionalmente el Flow
    private var isFirstRender: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initListeners()

    }

    private fun initUI() {

        if (isFirstRender) {
            CoroutineScope(Dispatchers.IO).launch {
                // Consumo el Flow
                getSettings().filter { isFirstRender }.collect { settings ->

                    if (settings != null) {
                        runOnUiThread {
                            binding.rgVolume.setValues(settings.volume.toFloat())
                            binding.swDarkMode.isChecked = settings.darkMode
                            binding.swVibration.isChecked = settings.vibration
                            binding.swBluetooth.isChecked = settings.bluetooth
                            isFirstRender = false
                        }
                    }
                }
            }
        }

    }

    private fun initListeners() {
        binding.rgVolume.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                saveRangesSlides(KEY_VOLUME_LVL, value.toInt())
            }
        }

        binding.swBluetooth.setOnCheckedChangeListener { _, b ->
            CoroutineScope(Dispatchers.IO).launch {
                saveSwitches(KEY_BLUETOOTH, b)
            }
        }

        binding.swVibration.setOnCheckedChangeListener { _, b ->
            CoroutineScope(Dispatchers.IO).launch {
                saveSwitches(KEY_VIBRATION, b)
            }
        }

        binding.swDarkMode.setOnCheckedChangeListener { _, b ->
            CoroutineScope(Dispatchers.IO).launch {
                saveSwitches(KEY_DARK_MODE, b)
            }
        }
    }

    private suspend fun saveSwitches(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    private suspend fun saveRangesSlides(key: String, value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    private fun getSettings(): Flow<SettingModel> {
        // Creo el Flow
        return dataStore.data.map { preferences ->
            SettingModel(
                volume = preferences[intPreferencesKey(KEY_VOLUME_LVL)] ?: 30,
                darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: true,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: false
            )
        }
    }


}