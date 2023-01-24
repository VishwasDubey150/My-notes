package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),InotesRVAdapter {
    lateinit var viewModel:NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        var rv=findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager=LinearLayoutManager(this)
        val adapter=NotesRVAdapter(this,this)
        rv.adapter=adapter

        viewModel=ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)
            }

        })


    }
    override fun onItemClicked(note: Note)
    {
        viewModel.deleteNode(note)
        Toast.makeText(this,"${note.text} Deleted!!",Toast.LENGTH_SHORT).show()
    }

    fun submit(view: View) {
        var et=findViewById<EditText>(R.id.et)
        val noteText=et.text.toString()
        if(noteText.isNotEmpty())
        {
            viewModel.insertnode(Note(noteText))
        }
    }
}