# 📒 Firebase MVVM Notes App (Jetpack Compose)

A simple **Notes app** built with **Jetpack Compose**, **MVVM architecture**, and **Firebase Firestore**.  
Supports **real-time sync**, **add/edit/delete notes**, and uses **Navigation Compose** for screen transitions.

---

## 🚀 Features
- MVVM architecture with **Repository** and **ViewModel**
- **Firestore** as the cloud database
- Real-time note updates using Firestore listeners
- Add, edit, and delete notes
- **Navigation Compose** for screen transitions
- Material 3 UI with Jetpack Compose

---

## 🛠 Tech Stack
- **Kotlin**
- **Jetpack Compose** (UI)
- **Firebase Firestore** (Database)
- **MVVM Architecture**
- **Navigation Compose** (Navigation)
- **Material 3**

---

## 📂 Project Structure
com.example.firebase/
├── model/
│ └── Note.kt
├── repository/
│ └── NoteRepository.kt
├── viewmodel/
│ └── NoteViewModel.kt
├── navigation/
│ └── NavGraph.kt
├── screens/
│ ├── NoteListScreen.kt
│ ├── AddNoteScreen.kt
│ └── EditNoteScreen.kt
└── MainActivity.kt
