package com.example.firebase.model


data class Note(
    val id: String = "",
    val text: String = ""
) {
    // Firestore needs a no-arg constructor for some usages, but Kotlin data class with defaults works.
}
