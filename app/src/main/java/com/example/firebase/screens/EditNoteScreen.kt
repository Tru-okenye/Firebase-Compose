package com.example.firebase.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firebase.model.Note
import com.example.firebase.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(viewModel: NoteViewModel, note: Note, onDone: () -> Unit) {
    var text by remember { mutableStateOf(note.text) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Edit Note") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Edit") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {
                    if (text.isNotBlank()) {
                        viewModel.updateNote(note.copy(text = text))
                        onDone()
                    }
                }) {
                    Text("Update")
                }
                Button(onClick = {
                    viewModel.deleteNote(note)
                    onDone()
                }) {
                    Text("Delete")
                }
            }
        }
    }
}
