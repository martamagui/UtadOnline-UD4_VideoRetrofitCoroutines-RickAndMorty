package com.mmag.ud4_videoretrofitconcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmag.ud4_videoretrofitconcoroutines.databinding.ActivityMainBinding
import com.mmag.ud4_videoretrofitconcoroutines.network.APIManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding

    private val adapter = CharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRickAndMorty.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRickAndMorty.adapter = adapter

        getAllCharacterFromAPI()
    }

    private fun getAllCharacterFromAPI() {
        lifecycleScope.launch(Dispatchers.IO) {
            val response = APIManager.service.getAllCharacters()

            if (response.isSuccessful) {
                //Llamaremos al hilo principal y pintemos la interfaz
                withContext(Dispatchers.Main) {
                    val body = response.body()
                    if (body != null) {
                        adapter.submitList(body.results)
                    } else {
                        Toast.makeText(this@MainActivity, "No llegó nada", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Algo fue mal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}