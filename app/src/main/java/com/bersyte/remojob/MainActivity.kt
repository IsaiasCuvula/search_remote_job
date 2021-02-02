package com.bersyte.remojob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bersyte.remojob.databinding.ActivityMainBinding
import com.bersyte.remojob.db.RemoteJobDatabase
import com.bersyte.remojob.repository.RemoteJobRepository
import com.bersyte.remojob.viewmodel.RemoteJobViewModel
import com.bersyte.remojob.viewmodel.RemoteJobViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RemoteJobViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        setUpViewModel()

    }


    private fun setUpViewModel() {

        val remoteJobRepository = RemoteJobRepository(
            RemoteJobDatabase(this)
        )

        val viewModelProviderFactory =
            RemoteJobViewModelFactory(
                application,
                remoteJobRepository
            )

        viewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(RemoteJobViewModel::class.java)

    }
}