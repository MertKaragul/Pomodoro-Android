package com.mertkaragul.pomodoro.Timer

import java.util.Date

interface ITimer {
    fun startTimer()

    fun createTimer(millisInFuture : Long ,isFinished : (Boolean) -> Unit, onTick : (Long) -> Unit)

    fun stopTimer()
}