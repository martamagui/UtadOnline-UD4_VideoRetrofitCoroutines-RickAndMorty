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

        //Configuramos la RecyclerView
        binding.rvRickAndMorty.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRickAndMorty.adapter = adapter

        getAllCharacterFromAPI()
    }

    private fun getAllCharacterFromAPI() {
        //Lanzamos una corrutina en el hilo secundario
        lifecycleScope.launch(Dispatchers.IO) {
            //Guardamos la llamada en una variable
            val response = APIManager.service.getAllCharacters()

            //Escuchamos si la respuesta fu exitosa
            if (response.isSuccessful) {
                //Llamaremos al hilo principal y pintemos la interfaz
                withContext(Dispatchers.Main) {
                    //Guardamos la respuesta del servidor
                    val body = response.body()
                    //Comprobamos que no sea nula para pintarla en la RecyclerView y si no mostramos un toast
                    if (body != null) {
                        adapter.submitList(body.results)
                    } else {
                        Toast.makeText(this@MainActivity, "No llegó nada", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                //Si la respuesta fue fallida pasamos al hilo principal y mostramos un toast
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Algo fue mal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}