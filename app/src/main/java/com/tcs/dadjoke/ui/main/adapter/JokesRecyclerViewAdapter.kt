package com.tcs.dadjoke.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tcs.dadjoke.data.model.Joke
import com.tcs.dadjoke.databinding.ItemJokeBinding

/**
 * Created by astirtrotter on 3/2/22
 */
class JokesRecyclerViewAdapter(
    private val jokes: List<Joke>,
    private val context: Context
): RecyclerView.Adapter<JokesRecyclerViewAdapter.JokeViewHolder>() {

    inner class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(joke: Joke) {
            val binding = ItemJokeBinding.bind(itemView)
            binding.txtValue.text = joke.joke
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(ItemJokeBinding.inflate(LayoutInflater.from(context), parent, false).root)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun getItemCount(): Int {
        return jokes.size
    }
}