package com.hnfnfl.finalproject.ui

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnfnfl.finalproject.adapter.FavoriteAnimeAdapter
import com.hnfnfl.finalproject.databinding.ActivityFavoriteAnimeBinding
import com.hnfnfl.finalproject.viewmodel.FavoriteAnimeViewModel
import com.hnfnfl.finalproject.viewmodel.ViewModelFactory

class FavoriteAnimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteAnimeBinding
    private lateinit var adapter: FavoriteAnimeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = obtainViewModel(this@FavoriteAnimeActivity)
        viewModel.apply {
            getFavoriteAnime().observe(this@FavoriteAnimeActivity) { anime ->
                if (anime != null) {
                    binding.empty.visibility = View.GONE
                    adapter.setList(anime)
                } else {
                    binding.empty.visibility = View.VISIBLE
                    adapter.setList(emptyList())
                }
            }
            adapter = FavoriteAnimeAdapter(this)
        }

        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                title = "Favorite Anime"
            }

            svAnimeFavorite.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    adapter.filter.filter(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    empty.visibility = if (adapter.itemCount == 0) View.VISIBLE else View.GONE
                    return true
                }
            })

            rvFavoriteAnime.apply {
                layoutManager = LinearLayoutManager(this@FavoriteAnimeActivity)
                setHasFixedSize(true)
                adapter = this@FavoriteAnimeActivity.adapter
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteAnimeViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteAnimeViewModel::class.java]
    }
}