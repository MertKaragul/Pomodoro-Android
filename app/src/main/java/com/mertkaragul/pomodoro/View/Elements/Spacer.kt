package com.mertkaragul.pomodoro.View.Elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun DefaultHeightSpacer(){
    val height = LocalConfiguration.current.screenHeightDp
    return Spacer(modifier = Modifier.height((height * 0.05).dp))
}

@Composable
fun DefaultWidthSpacer(){
    val width = LocalConfiguration.current.screenWidthDp
    return Spacer(modifier = Modifier.width((width * 0.05).dp))
}
