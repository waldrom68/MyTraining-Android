package com.rome.tech.mytraining.settings

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.net.wifi.WifiManager
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
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

// CONSTANTES con las claves para la persistencia de datos.
class SettingsActivity : AppCompatActivity() {
    companion object {
        const val KEY_VOLUME_LVL = "key_volume_lvl"
        const val KEY_VIBRATION = "key_vibration"
        const val KEY_WIFI = "key_wifi"
        const val KEY_DARK_MODE = "key_dark_mode"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var wifiManager: WifiManager? = null


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
                            binding.swWifi.isChecked = settings.wifi
                            isFirstRender = false
                        }
                    }
                }
            }
        }

        notifyDialog()

    }

    private fun initListeners() {
        binding.rgVolume.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                saveRangesSlides(KEY_VOLUME_LVL, value.toInt())
            }
        }

        binding.swWifi.setOnCheckedChangeListener { _, b ->
            CoroutineScope(Dispatchers.IO).launch {
                saveSwitches(KEY_WIFI, b)
                runOnUiThread {
                    navigateToSettingControls("Wifi")
                }

            }
        }

        binding.swVibration.setOnCheckedChangeListener { _, b ->
            CoroutineScope(Dispatchers.IO).launch {
                saveSwitches(KEY_VIBRATION, b)
            }
        }

        binding.swDarkMode.setOnCheckedChangeListener { _, dark ->
            CoroutineScope(Dispatchers.IO).launch {
                saveSwitches(KEY_DARK_MODE, dark)

                runOnUiThread {
                    if (dark) {
                        enableDarkMode()
                    } else {
                        disableDarkMode()
                    }
                }
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
                darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: isDarkTheme(this),
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: true,
                wifi = preferences[booleanPreferencesKey(KEY_WIFI)] ?: isWiFiEnabled()
            )
        }
    }


    fun isDarkTheme(activity: Activity): Boolean {
        return (activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }


    private fun isWiFiEnabled(): Boolean {
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wifiManager.isWifiEnabled
    }


    private fun navigateToSettingControls(control: String) {
        val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }

                DialogInterface.BUTTON_NEGATIVE -> {
                }
            }
        }

        var builder: AlertDialog.Builder = AlertDialog.Builder(this) as AlertDialog.Builder
        builder.setMessage("Do you want to open the $control controls?")
            .setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show()
    }

    private fun notifyDialog() {
        val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                }
            }
        }

        var builder: AlertDialog.Builder = AlertDialog.Builder(this) as AlertDialog.Builder
        builder.setMessage("IMPORTANTE: Solo tienen funcionalidad el DarkMode y la WIFI")
            .setNegativeButton("Ok", dialogClickListener).show()
    }
}

