package com.mertkaragul.pomodoro.View.Draw

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertkaragul.pomodoro.ui.theme.PomodoroTheme

@OptIn(ExperimentalTextApi::class)
@Composable
fun TimerDraw(
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    secondaryColor : Color = MaterialTheme.colorScheme.onPrimary,
    backgroundColor : Color = MaterialTheme.colorScheme.background,
    timerProgress : Float = .50f,
    modifier : Modifier = Modifier,
    timerToDraw : String){

    val textMeasurer = rememberTextMeasurer()
    val textToDraw = "Timer"
    val style = TextStyle(
        fontSize = 35.sp,
        color = Color.Black
    )
    val textLayoutResult = remember(textToDraw) {
        textMeasurer.measure(textToDraw, style)
    }

    Canvas(modifier = modifier , onDraw = {
        val startAngle = -90F
        val sweepAngle = (360F * timerProgress)
        drawArc(
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            color = primaryColor,
            useCenter = true,
            size = size
        )

        drawCircle(
            color = backgroundColor,
            radius = ((size.height + size.width) * .2).toFloat()
        )

        drawText(
            textMeasurer = textMeasurer,
            text = textToDraw,
            style = style,
            topLeft = Offset(
                x = center.x - textLayoutResult.size.width / 2,
                y = center.y - textLayoutResult.size.height / 2,
            )
        )

        drawText(
            textMeasurer = textMeasurer,
            text = timerToDraw,
            topLeft = Offset(
                x = (center.x - textLayoutResult.size.width / 2),
                y = (center.y - textLayoutResult.size.height / 2) + 120,
            ),
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black
            )
        )
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewTimerDraw(){
    PomodoroTheme {
        TimerDraw(
            modifier = Modifier.size(400.dp).padding(),
            timerToDraw = "00:00:00"
        )
    }
}