package com.mertkaragul.pomodoro.View.Layers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertkaragul.pomodoro.Enum.TimerStatus

@Composable
fun ViewStatus(status : TimerStatus){
    Column(
        modifier = Modifier
            .width(300.dp)
            .height(50.dp)
            .border(
                2.dp,
                MaterialTheme.colorScheme.onSecondary.copy(0.5F)
            )
            .clip(
                RoundedCornerShape(100.dp)
            )
            .background(
                MaterialTheme.colorScheme.secondary.copy(0.5F)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (status == TimerStatus.POMODORO_TIME){
                "Çalışma vakti"
            }else if (status == TimerStatus.BREAK_TIME) {
                "Dinlenme vakti"
            }else{
                 "Planlama"
                 },
            fontSize = 24.sp,
            fontWeight = FontWeight.W500
        )
    }
}