package com.example.mvvm_learning

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_learning.models.QuoteList


class QuoteAdapter(private val context: Context, private val quotes: LiveData<QuoteList>) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    inner class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        val quoteTextView: TextView = itemView.findViewById(R.id.quoteTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quoteList = quotes.value
        val quote = quoteList?.results?.get(position)
        holder.authorTextView.text = quote!!.author
        holder.quoteTextView.text = quote.content


    }

    override fun getItemCount(): Int {
        return quotes.value?.results?.count() ?: 0
    }
}
