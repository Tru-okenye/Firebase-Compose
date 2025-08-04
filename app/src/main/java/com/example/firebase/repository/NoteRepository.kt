package com.example.firebase.repository


import com.example.firebase.model.Note
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class NoteRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("notes")

    // Real-time stream of notes
    fun observeNotes() = callbackFlow<List<Note>> {
        val listener = collection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val notes = snapshot?.documents
                ?.mapNotNull { doc ->
                    val text = doc.getString("text") ?: return@mapNotNull null
                    Note(id = doc.id, text = text)
                } ?: emptyList()
            trySend(notes)
        }
        awaitClose { listener.remove() }
    }

    // Add new note
    suspend fun addNote(text: String) {
        val data = mapOf("text" to text)
        collection.add(data).await()
    }

    // Update existing note
    suspend fun updateNote(note: Note) {
        if (note.id.isBlank()) return
        collection.document(note.id)
            .set(mapOf("text" to note.text))
            .await()
    }

    // Delete
    suspend fun deleteNote(note: Note) {
        if (note.id.isBlank()) return
        collection.document(note.id).delete().await()
    }
}
