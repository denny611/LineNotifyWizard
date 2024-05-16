package com.daniel.linenotifywizard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.daniel.linenotifywizard.domain.NotifyMangerService
import com.daniel.linenotifywizard.ui.theme.LineNotifyWizardTheme


class MainActivity : ComponentActivity() {
    lateinit var mActivity:Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        setContent {
            LineNotifyWizardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    FilledButton()
}

@Composable
fun FilledButton() {
    // Create a launcher for starting the activity
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { /* Handle the result if needed */ }
    Button(
        onClick = {
            val intent = Intent(android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            // Start the activity using the launcher
            launcher.launch(intent)
        }
    ){
        Text("Request Permission")
    }
    LocalContext.current.startService(Intent( LocalContext.current, NotifyMangerService::class.java))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LineNotifyWizardTheme {
        Greeting("Android")
    }
}
