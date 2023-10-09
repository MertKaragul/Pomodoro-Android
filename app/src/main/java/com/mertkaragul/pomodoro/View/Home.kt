package com.mertkaragul.pomodoro.View

import android.content.Context
import android.view.View
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mertkaragul.pomodoro.Enum.TimerStatus
import com.mertkaragul.pomodoro.R
import com.mertkaragul.pomodoro.Service.Notification.NotificationService
import com.mertkaragul.pomodoro.View.Permission.Permission
import com.mertkaragul.pomodoro.View.Elements.DefaultHeightSpacer
import com.mertkaragul.pomodoro.View.Layers.ActionView
import com.mertkaragul.pomodoro.View.Layers.TimerView
import com.mertkaragul.pomodoro.View.Layers.ViewStatus
import com.mertkaragul.pomodoro.ViewModel.HomeViewmodel
import com.mertkaragul.pomodoro.ui.theme.PomodoroTheme

@Composable
fun Home(
    homeViewmodel: HomeViewmodel = viewModel(),
    context : Context = LocalContext.current
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val time = homeViewmodel.time.observeAsState().value ?: "00:00"
        val timerStatus = homeViewmodel.timerStatus.value ?: TimerStatus.POMODORO_TIME
        var pauseTimer by remember { mutableStateOf(false) }
        
        // Launch permissions
        Permission()

        ViewStatus(timerStatus)

        DefaultHeightSpacer()

        TimerView(time)

        DefaultHeightSpacer()
        ActionView(
            start = { homeViewmodel.start() },
            pause = {
                homeViewmodel.pause(pauseTimer)
                pauseTimer = !pauseTimer
            },
            cancel = { homeViewmodel.cancel()}
        )


        LaunchedEffect(key1 = time){
            val permission = NotificationService()
            if ((time == "00:00") || (time == "00:01")) {
                val text = if (timerStatus == TimerStatus.POMODORO_TIME){
                    "Çalışma vaktin bitti"
                }else if( timerStatus == TimerStatus.BREAK_TIME){
                    "Dinlenme vaktin bitti"
                } else {
                    ""
                }

                if (text != ""){
                    permission.sendNotification(context, R.string.app_name.toString(),
                        description = text
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    PomodoroTheme {
        Home()
    }
}
