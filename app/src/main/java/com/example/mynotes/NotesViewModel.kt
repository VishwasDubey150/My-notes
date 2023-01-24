package com.example.mynotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application){

    val repo:NoteRepo
    val allNotes: LiveData<List<Note>>

    init {
        val dao=NoteDatabase.getDatabase(application).getNoteDa()
        repo=NoteRepo(dao)
        allNotes=repo.allNotes
    }

    fun deleteNode(note:Note)=viewModelScope.launch {
        repo.delete(note)
    }

    fun insertnode(note:Note)=viewModelScope.launch {
        repo.insert(note)
    }



}