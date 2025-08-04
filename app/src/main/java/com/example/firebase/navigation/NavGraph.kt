package com.example.firebase.navigation


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.firebase.model.Note
import com.example.firebase.screens.AddNoteScreen
import com.example.firebase.screens.EditNoteScreen
import com.example.firebase.screens.NoteListScreen
import com.example.firebase.viewmodel.NoteViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavGraph(viewModel: NoteViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        composable("list") {
            NoteListScreen(
                viewModel = viewModel,
                onAdd = { navController.navigate("add") },
                onEdit = { note ->
                    // encode note id to safe string
                    navController.navigate("edit/${URLEncoder.encode(note.id, StandardCharsets.UTF_8.name())}")
                }
            )
        }
        composable("add") {
            AddNoteScreen(viewModel) {
                navController.popBackStack()
            }
        }
        composable(
            "edit/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { back ->
            val noteId = back.arguments?.getString("noteId") ?: return@composable
            val note = viewModel.notes.value.find { it.id == noteId }
            note?.let {
                EditNoteScreen(viewModel = viewModel, note = it) {
                    navController.popBackStack()
                }
            }
        }
    }
}
