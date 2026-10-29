package com.denalal.dayly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.denalal.dayly.core.ui.theme.DaylyTheme
import com.denalal.dayly.feature.medications.MedicationsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DaylyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MedicationsScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}