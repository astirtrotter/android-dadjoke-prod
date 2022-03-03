package com.tcs.dadjoke.ui.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tcs.dadjoke.data.model.Joke
import com.tcs.dadjoke.databinding.ItemJokeBinding
import com.tcs.dadjoke.ui.main.viewmodel.MainViewModel
import okhttp3.internal.notify

/**
 * Created by astirtrotter on 3/2/22
 */
class JokesRecyclerViewAdapter(
    private val context: Context,
    private val viewModel: MainViewModel,
    private val jokes: List<Joke>,
): RecyclerView.Adapter<JokesRecyclerViewAdapter.JokeViewHolder>() {

    inner class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(joke: Joke, position: Int) {
            val binding = ItemJokeBinding.bind(itemView)
            binding.txtValue.text = joke.joke
            if (joke.isNew) {
                binding.txtValue.alpha = 1.0f
                binding.btnSave.isEnabled = true
            } else {
                binding.txtValue.alpha = 0.5f
                binding.btnSave.isEnabled = false
            }
            binding.chkLike.isChecked = joke.isLiked
            binding.btnSave.setOnClickListener {
                viewModel.save(joke)
                notifyItemChanged(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(ItemJokeBinding.inflate(LayoutInflater.from(context), parent, false).root)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokes[position], position)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }
}