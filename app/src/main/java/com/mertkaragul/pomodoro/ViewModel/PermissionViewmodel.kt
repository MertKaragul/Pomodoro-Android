package com.mertkaragul.pomodoro.ViewModel

import androidx.lifecycle.ViewModel

class PermissionViewmodel : ViewModel(){
    val visiblePermissionDialogQueue = mutableListOf<String>()

    fun dismissDialog() = visiblePermissionDialogQueue.removeLast()

    fun onPermissionResult(
        permission : String,
        isGranted : Boolean
    ){
        if (!isGranted){
            visiblePermissionDialogQueue.add(0, permission)
        }
    }
}