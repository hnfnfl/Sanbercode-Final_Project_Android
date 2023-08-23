package com.hnfnfl.finalproject.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hnfnfl.finalproject.R
import com.hnfnfl.finalproject.databinding.ActivityAddMenuBinding
import com.hnfnfl.finalproject.db.Menu
import com.hnfnfl.finalproject.repository.ViewModelFactory

class AddMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMenuBinding
    private lateinit var addMenuViewModel: AddMenuViewModel

    private var menu: Menu? = null
    private var isEdit = false

    companion object {
        const val EXTRA_MENU = "extra_menu"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addMenuViewModel = obtainViewModel(this@AddMenuActivity)

        menu = intent.getParcelableExtra(EXTRA_MENU)
        if (menu != null) {
            isEdit = true
        } else {
            menu = Menu()
        }

        val title: String
        binding.apply {
            if (isEdit) {
                title = getString(R.string.edit)
                if (menu != null) {
                    menu?.let { menu ->
                        inputMenuName.setText(menu.name)
                        inputMenuDesc.setText(menu.description)
                        inputMenuPrice.setText(menu.price.toString())
                    }
                }
            } else {
                title = getString(R.string.tambah)
            }

            supportActionBar?.title = title
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            btn.text = title
            btn.setOnClickListener {
                val name = inputMenuName.text.toString().trim()
                val desc = inputMenuDesc.text.toString().trim()
                val price = inputMenuPrice.text.toString().trim()
                when {
                    name.isEmpty() -> {
                        inputMenuName.error = getString(R.string.empty)
                    }
                    desc.isEmpty() -> {
                        inputMenuDesc.error = getString(R.string.empty)
                    }
                    price.isEmpty() -> {
                        inputMenuPrice.error = getString(R.string.empty)
                    }
                    else -> {
                        menu?.name = name
                        menu?.description = desc
                        menu?.price = price.toInt()
                        if (isEdit) {
                            addMenuViewModel.updateMenu(menu as Menu)
                            Toast.makeText(this@AddMenuActivity, "Menu Berhasil Diubah", Toast.LENGTH_SHORT).show()
                        } else {
                            addMenuViewModel.insertMenu(menu as Menu)
                            Toast.makeText(this@AddMenuActivity, "Menu Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                        }
                        finish()
                    }
                }
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): AddMenuViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[AddMenuViewModel::class.java]
    }
}