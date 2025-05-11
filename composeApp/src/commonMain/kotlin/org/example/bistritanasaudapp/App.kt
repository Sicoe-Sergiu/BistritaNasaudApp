package org.example.bistritanasaudapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val firestore = remember { Firebase.firestore }
        val scope = rememberCoroutineScope()
        var locationData by remember { mutableStateOf<String?>(null) }

        LaunchedEffect(Unit) {
            scope.launch {
                val first = firestore.collection("location")
                    .limit(5)
                    .get()
                    .documents
                first.forEach {
                    locationData += it.toString()
                }
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = locationData ?: "Loading...",
                style = MaterialTheme.typography.body1
            )
        }
    }
}
