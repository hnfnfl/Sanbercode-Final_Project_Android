package com.hnfnfl.finalproject.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnfnfl.finalproject.R
import com.hnfnfl.finalproject.databinding.ActivityMainBinding
import com.hnfnfl.finalproject.viewmodel.MainViewModel
import com.hnfnfl.finalproject.viewmodel.ViewModelFactory
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
//    private lateinit var topAnimeAdapter: TopAnimeAdapter
    private lateinit var upcomingAnimeAdapter: TopAnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(true)
        }

        val viewModel = obtainViewModel(this@MainActivity)
        viewModel.apply {
            getTopAnime()
            getUpcomingAnime()
//            topAnimeLiveData.observe(this@MainActivity) { anime ->
//                if (anime != null) {
//                    topAnimeAdapter.setTopAnime(anime)
//                    topAnimeAdapter.notifyDataSetChanged()
//                }
//            }
            upcomingAnimeLiveData.observe(this@MainActivity) { anime ->
                if (anime != null) {
                    upcomingAnimeAdapter.setUpcomingAnime(anime)
                    upcomingAnimeAdapter.notifyDataSetChanged()
                }
            }
//            topAnimeAdapter = TopAnimeAdapter(viewModel, true)
            upcomingAnimeAdapter = TopAnimeAdapter(viewModel, false)
        }

        binding.apply {
            rvTopAnime.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = upcomingAnimeAdapter
            }

            rvUpcomingAnime.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = upcomingAnimeAdapter
            }

            btnFavoriteAnime.setOnClickListener {
                Toasty.info(this@MainActivity, "Coming soon").show()
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }
}