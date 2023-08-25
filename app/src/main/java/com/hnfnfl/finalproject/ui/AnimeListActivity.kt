package com.hnfnfl.finalproject.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
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
        val animeList = resources.getStringArray(R.array.random_anime)
        val randomAnime = animeList.random()

        viewModel.apply {
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
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                title = "Anime List"
            }

            svAnime.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrEmpty()) {
                        viewModel.getAnime(query)
                    } else {
                        viewModel.getAnime(randomAnime)
                    }
                    empty.visibility = if (adapter.itemCount == 0) {
                        Toasty.info(this@AnimeListActivity, "Anime $query not found", Toasty.LENGTH_SHORT).show()
                        View.VISIBLE
                    } else {
                        View.GONE
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
                startActivity(Intent(this@AnimeListActivity, FavoriteAnimeActivity::class.java))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
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