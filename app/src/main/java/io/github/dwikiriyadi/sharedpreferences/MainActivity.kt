package io.github.dwikiriyadi.sharedpreferences

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.github.dwikiriyadi.sharedpreferences.prefs.ExamplePreferences
import io.github.dwikiriyadi.sharedpreferences.ui.theme.ImprovedSharedPreferencesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImprovedSharedPreferencesTheme {
                val prefs = ExamplePreferences(this)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(prefs, "Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(prefs: ExamplePreferences, name: String) {
    Column(Modifier.fillMaxSize()) {
        var myName by remember { mutableStateOf("") }
        var count by remember { mutableStateOf(0) }
        var myMoney by remember { mutableStateOf("") }

        var data by remember { mutableStateOf("") }

        TextField(value = myName, onValueChange = { myName = it }, label = { Text("Name") })
        TextField(value = myMoney, onValueChange = { myMoney = it }, label = { Text("My Money") })

        Text(text = data)

        TextButton(onClick = {
            data =
                "name: ${prefs.name}, count: ${prefs.count}, myMoney: ${prefs.myMoney}, isMySelf: ${prefs.isMyself}, modFromCount: ${prefs.modFromCount} "
        }) {
            Text(text = "Show Data")
        }

        TextButton(onClick = {
            prefs.name = myName
            prefs.count = count++
            prefs.myMoney = myMoney.toLong()
            prefs.isMyself = myName == "Dwiki Riyadi"
            prefs.modFromCount = (count * 10 / 2).toFloat()
        }) {
            Text(text = "Save")
        }
    }
}