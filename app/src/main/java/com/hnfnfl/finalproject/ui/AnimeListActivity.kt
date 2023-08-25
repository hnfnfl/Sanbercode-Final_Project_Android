package com.hnfnfl.finalproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnfnfl.finalproject.R
import com.hnfnfl.finalproject.adapter.AnimeListAdapter
import com.hnfnfl.finalproject.databinding.ActivityAnimeListBinding
import com.hnfnfl.finalproject.viewmodel.AnimeListViewModel
import com.hnfnfl.finalproject.viewmodel.ViewModelFactory
import es.dmoral.toasty.Toasty

class AnimeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeListBinding
    private lateinit var adapter: AnimeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = obtainViewModel(this@AnimeListActivity)
        viewModel.apply {
            val animeList = resources.getStringArray(R.array.random_anime)
            val randomAnime = animeList.random()
            getAnime(randomAnime)
            liveData.observe(this@AnimeListActivity) { anime ->
                if (anime != null) {
                    adapter.setList(anime)
                } else {
                    adapter.setList(emptyList())
                }
            }
            adapter = AnimeListAdapter(this)
        }

        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(false)
                setDisplayShowHomeEnabled(true)
                title = "Anime List"
            }

            svAnime.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.getAnime(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

            rvMenu.apply {
                layoutManager = LinearLayoutManager(this@AnimeListActivity)
                setHasFixedSize(true)
                adapter = this@AnimeListActivity.adapter
            }

            btnFavoriteAnime.setOnClickListener {
                Toasty.info(this@AnimeListActivity, "Coming soon").show()
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): AnimeListViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[AnimeListViewModel::class.java]
    }

    companion object {
        fun newIntent(activity: AppCompatActivity): Intent {
            return Intent(activity, AnimeListActivity::class.java)
        }
    }
}