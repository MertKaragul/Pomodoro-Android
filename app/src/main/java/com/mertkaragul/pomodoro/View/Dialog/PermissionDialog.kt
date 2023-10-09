package com.mertkaragul.pomodoro.View.Dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PermissionDialog(
    permission : String,
    isPermanentlyDeclined : Boolean,
    onDismiss : () -> Unit,
    onOkClick : () -> Unit,
    onGoToAppSettingsClick : () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Divider()
            val text = if (isPermanentlyDeclined) "İzin ver" else  "Tamam"
            Text(text = text, modifier = Modifier.fillMaxWidth().clickable { if (isPermanentlyDeclined){onGoToAppSettingsClick()} else{ onOkClick() } })
        },
        title = {
            Text(text = "Zorunlu izin")
        },
        text = {

        },
        modifier = modifier,
    )
}