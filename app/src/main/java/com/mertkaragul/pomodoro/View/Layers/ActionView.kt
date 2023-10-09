package com.mertkaragul.pomodoro.View.Layers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ActionView(
    start : () -> Unit,
    pause : () -> Unit,
    cancel : () -> Unit,
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {start()}){
            Text(text = "Başlat")
        }
        Button(onClick = {
            pause()
        }) {
            Text(text = "Durdur")
        }
        Button(onClick = { cancel() }) {
            Text(text = "İptal")
        }
    }
}