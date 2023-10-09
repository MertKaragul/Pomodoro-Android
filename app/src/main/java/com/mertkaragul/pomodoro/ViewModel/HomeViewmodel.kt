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

    val time = MutableLiveData<String>("00:00")
    val longTime = MutableLiveData<Long>(0)

    val timerStatus = MutableLiveData<TimerStatus>(TimerStatus.TIMER_SETUP)

    fun start(
        pomodoroTime : Long = POMODORO_TIME,
        breakTime: Long = BREAK_TIME
    ){
        setupTimers(pomodoroTime,breakTime)
        when(timerStatus.value){
            TimerStatus.TIMER_SETUP -> {
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

    fun pause(status : Boolean){
        when(timerStatus.value){
            TimerStatus.POMODORO_TIME -> {
                pomodoroTimer.stopTimer()
                if(status){
                    start(
                        pomodoroTime = longTime.value ?: POMODORO_TIME
                    )
                }
            }

            TimerStatus.BREAK_TIME -> {
                breakTimer.stopTimer()
                if(status){
                    start(
                        breakTime = longTime.value ?: BREAK_TIME
                    )
                }
            }

            else -> {

            }
        }
    }

    fun cancel(){
        pomodoroTimer.stopTimer()
        breakTimer.stopTimer()

        timerStatus.value = TimerStatus.TIMER_SETUP
        time.value = "00:00"
    }


    private fun setupTimers(
        pomodoroTime: Long = POMODORO_TIME,
        breakTime:Long = BREAK_TIME
    ){
        pomodoroTimer.createTimer(
            pomodoroTime,
            isFinished = {
                timerStatus.value = TimerStatus.BREAK_TIME
                breakTimer.startTimer()
            },
            onTick = {
                time.value = simpleDateFormat.format(Date(it)).toString()
                longTime.value = it
            }
        )

        breakTimer.createTimer(
            breakTime,
            isFinished = {
                timerStatus.value = TimerStatus.POMODORO_TIME
                pomodoroTimer.startTimer()
            },
            onTick = {
                time.value = simpleDateFormat.format(Date(it)).toString()
                longTime.value = it
            }
        )
    }
}