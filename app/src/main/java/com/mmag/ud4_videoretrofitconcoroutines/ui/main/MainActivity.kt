package com.mmag.ud4_videoretrofitconcoroutines.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmag.ud4_videoretrofitconcoroutines.databinding.ActivityMainBinding
import com.mmag.ud4_videoretrofitconcoroutines.ui.CharacterAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding

    private val adapter = CharacterAdapter()

    //Viewmodel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuramos la RecyclerView
        binding.rvRickAndMorty.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRickAndMorty.adapter = adapter

        //getAllCharacterFromAPI()
        observeViewModelResponse()
        viewModel.getAllCharacterFromAPI()
    }

    private fun observeViewModelResponse() {
        //Escuchamos
        viewModel.charactersResponse.observe(this) { uiState ->
            if (uiState.response != null) {
                adapter.submitList(uiState.response.results)
            }

            if (uiState.isError) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }

            if (uiState.isLoading) {
                //mostramos
                binding.pbList.visibility = View.VISIBLE
            } else {
                //escondemos spinner
                binding.pbList.visibility = View.GONE
            }
        }
    }
}