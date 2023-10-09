package com.mertkaragul.pomodoro.View.Layers

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TimerView(time : String){
    LazyRow{
        items(time.length){ it ->
            Column(modifier = Modifier
                .height(100.dp)
                .width(50.dp)
                .border(
                    2.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.5F),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5F)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                AnimatedContent(
                    targetState = time[it],
                    transitionSpec = {
                        slideInVertically { it } togetherWith slideOutVertically { -it }
                    },
                    label = "",
                    modifier = Modifier.padding()
                ){
                    Text(it.toString(), textAlign = TextAlign.Center, modifier = Modifier.padding())
                }
            }
        }
    }
}