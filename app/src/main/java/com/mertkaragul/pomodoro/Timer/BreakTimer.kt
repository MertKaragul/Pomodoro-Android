package com.mertkaragul.pomodoro.Timer

import android.os.CountDownTimer
import java.text.SimpleDateFormat
import java.util.Date

class BreakTimer : ITimer {
    companion object{
        val BREAK_TIME: Long = 300000
    }
    private var countDownTimer : CountDownTimer? = null

    override fun createTimer(millisInFuture : Long ,isFinished: (Boolean) -> Unit, onTick: (Long) -> Unit) {
        countDownTimer = null
        countDownTimer = object : CountDownTimer(millisInFuture,1000){
            override fun onTick(p0: Long) {
                onTick(p0)
            }

            override fun onFinish() {
                isFinished(true)
            }

        }
    }

    override fun startTimer() {
        countDownTimer?.start()
    }

    override fun stopTimer() {
        countDownTimer?.cancel()
    }
}