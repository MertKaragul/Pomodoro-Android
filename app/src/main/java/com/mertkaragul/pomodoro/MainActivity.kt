package com.mertkaragul.pomodoro

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.getSystemService
import com.mertkaragul.pomodoro.Service.Notification.NotificationService
import com.mertkaragul.pomodoro.View.Home
import com.mertkaragul.pomodoro.ui.theme.PomodoroTheme

class MainActivity : ComponentActivity() {
    private val notificationService = NotificationService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationService.createNotificationChannel(applicationContext)
        setContent {
            PomodoroTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Home()
                }
            }
        }
    }
}

