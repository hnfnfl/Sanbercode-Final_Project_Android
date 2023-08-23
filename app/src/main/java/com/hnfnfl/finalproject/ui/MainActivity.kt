package com.hnfnfl.finalproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnfnfl.finalproject.databinding.ActivityMainBinding
import com.hnfnfl.finalproject.repository.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val mainViewModel = obtainViewModel(this@MainActivity)
        mainViewModel.getALlMenu().observe(this@MainActivity) { menuList ->
            if (menuList != null) {
                adapter.setList(menuList)
            }
        }
        adapter = MenuAdapter(mainViewModel)

        binding?.apply {
            rvMenu.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = this@MainActivity.adapter
            }

            fabAdd.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddMenuActivity::class.java))
            }
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }
}