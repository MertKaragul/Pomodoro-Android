package com.mertkaragul.pomodoro.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.pomodoro.Enum.TimerStatus
import com.mertkaragul.pomodoro.Service.Notification.NotificationService
import com.mertkaragul.pomodoro.Timer.BreakTimer
import com.mertkaragul.pomodoro.Timer.BreakTimer.Companion.BREAK_TIME
import com.mertkaragul.pomodoro.Timer.PomodoroTimer
import com.mertkaragul.pomodoro.Timer.PomodoroTimer.Companion.POMODORO_TIME
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class HomeViewmodel : ViewModel() {
    private val pomodoroTimer = PomodoroTimer()
    private val breakTimer = BreakTimer()
    private val simpleDateFormat = SimpleDateFormat("mm:ss")
    private var working = false


    val time = MutableLiveData<String>("00:00")
    private var longTime : Long = 0

    val timerStatus = MutableLiveData<TimerStatus>(TimerStatus.TIMER_SETUP)

    fun start(
        pomodoroTime : Long = POMODORO_TIME,
        breakTime : Long = BREAK_TIME
    ){
        if (!working){
            pomodoroTimer.stopTimer()
            breakTimer.stopTimer()

            pomodoroSetup(pomodoroTime)
            breakTimerSetup(breakTime)

            when(timerStatus.value){
                TimerStatus.TIMER_SETUP -> {
                    working = true
                    timerStatus.value = TimerStatus.POMODORO_TIME
                    pomodoroTimer.startTimer()
                }

                TimerStatus.POMODORO_TIME -> {
                    timerStatus.value = TimerStatus.POMODORO_TIME
                    pomodoroTimer.startTimer()
                }

                TimerStatus.BREAK_TIME -> {
                    timerStatus.value = TimerStatus.BREAK_TIME
                    breakTimer.startTimer()
                }

                else -> {

                }
            }
        }
    }

    fun pause(status : Boolean){
        if (working){
            when(timerStatus.value){
                TimerStatus.POMODORO_TIME -> {
                    pomodoroTimer.stopTimer()
                    if(status){
                        working = false
                        start(
                            pomodoroTime = longTime
                        )
                        working = true
                    }
                }

                TimerStatus.BREAK_TIME -> {
                    breakTimer.stopTimer()
                    if (status){
                        working = false
                        start(
                            breakTime = longTime
                        )
                        working = true
                    }
                }

                else -> {

                }
            }
        }
    }

    fun cancel(){
        pomodoroTimer.stopTimer()
        breakTimer.stopTimer()
        timerStatus.value = TimerStatus.TIMER_SETUP
        time.value = "00:00"
        working = false
    }


    private fun breakTimerSetup(breakTime:Long){
        breakTimer.createTimer(
            breakTime,
            isFinished = {
                timerStatus.value = TimerStatus.POMODORO_TIME
                pomodoroTimer.startTimer()
            },
            onTick = {
                time.value = simpleDateFormat.format(Date(it)).toString()
                longTime = it
            }
        )
    }

    private fun pomodoroSetup(pomodoroTime: Long){
        pomodoroTimer.createTimer(
            pomodoroTime,
            isFinished = {
                timerStatus.value = TimerStatus.BREAK_TIME
                breakTimer.startTimer()
            },
            onTick = {
                time.value = simpleDateFormat.format(Date(it)).toString()
                longTime = it
                println("Tick : $it & longTime : $longTime")
            }
        )
    }
}