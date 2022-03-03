package com.tcs.dadjoke.ui.main.view

import android.R
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
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
        binding.btnSearch.setOnClickListener { viewModel.search(binding.inputSearch.text.toString()) }
        initializeAdapter()
    }

    private fun initializeAdapter() {
        // orientation
        binding.rvJokes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // item separator
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        val accentColor = TypedValue()
        theme.resolveAttribute(R.attr.colorAccent, accentColor, true)
        dividerItemDecoration.setDrawable(ColorDrawable(accentColor.data))
        binding.rvJokes.addItemDecoration(dividerItemDecoration)

        // data observer
        observeData()
    }

    private fun observeData() {
        viewModel.displayData.observe(this) { jokes ->
            binding.rvJokes.adapter = JokesRecyclerViewAdapter(this, viewModel, jokes)
        }
        viewModel.loadingStatusData.observe(this) { loading ->
            if (loading) {
                binding.spinner.show()
            } else {
                binding.spinner.hide()
            }
        }
    }
}