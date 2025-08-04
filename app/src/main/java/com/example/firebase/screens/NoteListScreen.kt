package com.example.firebase.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firebase.model.Note
import com.example.firebase.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    viewModel: NoteViewModel,
    onAdd: () -> Unit,
    onEdit: (Note) -> Unit
) {
    val notes by viewModel.notes.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Firebase Notes") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            error?.let {
                Text("Error: $it", color = MaterialTheme.colorScheme.error)
            }
            if (notes.isEmpty()) {
                Text("No notes yet.")
            }
            notes.forEach { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { onEdit(note) }
                ) {
                    Text(note.text, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
