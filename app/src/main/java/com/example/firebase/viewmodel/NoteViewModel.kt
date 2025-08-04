package com.example.firebase.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.model.Note
import com.example.firebase.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    private val repository = NoteRepository()

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        repository.observeNotes()
            .onEach { list ->
                _notes.value = list
            }
            .catch { e ->
                _error.value = e.message
            }
            .launchIn(viewModelScope)
    }

    fun addNote(text: String) {
        viewModelScope.launch {
            try {
                repository.addNote(text)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            try {
                repository.updateNote(note)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            try {
                repository.deleteNote(note)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
