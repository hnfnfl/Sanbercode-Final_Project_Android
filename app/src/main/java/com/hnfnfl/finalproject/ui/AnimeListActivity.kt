package com.hnfnfl.finalproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnfnfl.finalproject.databinding.ActivityAnimeListBinding
import com.hnfnfl.finalproject.viewmodel.AnimeListViewModel
import com.hnfnfl.finalproject.viewmodel.ViewModelFactory

class AnimeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeListBinding
    private lateinit var adapter: AnimeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = obtainViewModel(this@AnimeListActivity)
        viewModel.getAnime("naruto")
        viewModel.liveData.observe(this@AnimeListActivity) { anime ->
            if (anime != null) {
                adapter.setList(anime)
            } else {
                adapter.setList(emptyList())
            }
        }
        adapter = AnimeListAdapter(viewModel)

        binding.apply {
            rvMenu.apply {
                layoutManager = LinearLayoutManager(this@AnimeListActivity)
                setHasFixedSize(true)
                adapter = this@AnimeListActivity.adapter
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): AnimeListViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[AnimeListViewModel::class.java]
    }

    companion object {
        fun getIntent(activity: AppCompatActivity): Intent {
            return Intent(activity, AnimeListActivity::class.java)
        }
    }
}