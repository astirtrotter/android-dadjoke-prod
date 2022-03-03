package com.tcs.dadjoke.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcs.dadjoke.databinding.ActivityMainBinding
import com.tcs.dadjoke.ui.main.adapter.JokesRecyclerViewAdapter
import com.tcs.dadjoke.ui.main.viewmodel.MainViewModel
import com.tcs.dadjoke.ui.main.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory()).get(MainViewModel::class.java)

        binding.btnNew.setOnClickListener { viewModel.add() }
        initializeAdapter()
    }

    private fun initializeAdapter() {
        binding.rvJokes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        observeData()
    }

    private fun observeData() {
        viewModel.liveData.observe(this) { jokes ->
            binding.rvJokes.adapter = JokesRecyclerViewAdapter(jokes, this)
        }
    }
}