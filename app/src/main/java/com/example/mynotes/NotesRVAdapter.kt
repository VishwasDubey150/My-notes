package com.example.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(val context: Context,val listener: InotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    val allNote=ArrayList<Note>()

    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textView=itemView.findViewById<TextView>(R.id.text)
        val delete=itemView.findViewById<ImageView>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))

        viewHolder.delete.setOnClickListener{
            listener.onItemClicked(allNote[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNode=allNote[position]
        holder.textView.text=currentNode.text
    }

    override fun getItemCount(): Int {
        return allNote.size
    }

    fun updateList(newList:List<Note>)
    {
        allNote.clear()
        allNote.addAll(newList)

        notifyDataSetChanged()
    }
}

interface InotesRVAdapter{
    fun onItemClicked(note: Note)
}