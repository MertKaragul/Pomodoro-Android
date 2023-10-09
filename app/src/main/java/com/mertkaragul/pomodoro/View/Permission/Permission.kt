package com.mertkaragul.pomodoro.View.Permission

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mertkaragul.pomodoro.Enum.PermissionAcceptStatus
import com.mertkaragul.pomodoro.ViewModel.PermissionViewmodel
import com.mertkaragul.pomodoro.ui.theme.PomodoroTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(){
    val permissionState = rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)
    var permissionStatus by remember { mutableStateOf(PermissionAcceptStatus.PERMISSION_ASKED) }
    val permissionRequestLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it) permissionStatus = PermissionAcceptStatus.PERMISSION_ACCEPT
            else permissionStatus = PermissionAcceptStatus.PERMISSION_DENIED
        }
    )

    LaunchedEffect(permissionState) {
        if (!permissionState.status.isGranted) {
            permissionRequestLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    if(permissionStatus == PermissionAcceptStatus.PERMISSION_DENIED){
        Toast.makeText(LocalContext.current, "İzin reddedildi, uygulama size durum bildirim sağlamaz" ,Toast.LENGTH_LONG).show()
    }
}


@Preview
@Composable
fun PreviewPermission(){
    PomodoroTheme {
        Permission()
    }
}