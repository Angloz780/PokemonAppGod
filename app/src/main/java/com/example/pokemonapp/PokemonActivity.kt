package com.example.pokemonapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokemonapp.databinding.ActivityPokemonBinding
import com.squareup.picasso.Picasso

class PokemonActivity : AppCompatActivity() {

    companion object {
        const val POKEMON_TAG = "Pokemon"
        fun start(pokemon: Pokemon, context: Context) {
            val intent = Intent(context, PokemonActivity::class.java)
            intent.putExtra(POKEMON_TAG, pokemon.toJson())
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityPokemonBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonJson = intent.getStringExtra(POKEMON_TAG)
        if (pokemonJson != null) {
            val pokemon = Pokemon.fromJson(pokemonJson)
            Picasso.get().load(pokemon.sprites.frontDefault).into(binding.ivPokemon)
            pokemon.iniciarVida()

            binding.tvPokemonNombre.text ="Id:  ${pokemon.id}, Nombre: ${pokemon.nameCapitalized()}"
            binding.tvPokemonAltura.text = "Altura: ${pokemon.height} m"
            binding.tvPokemonPeso.text = "Peso: ${pokemon.weight} kg"
            binding.vida.max = pokemon.vidaMax
            binding.vida.progress = pokemon.vidaActual

            binding.vida.apply {
                max = pokemon.vidaMax
                progress = pokemon.vidaActual
                progressTintList = ColorStateList.valueOf(
                    when{
                        pokemon.vidaActual < pokemon.vidaMax * 0.15 -> Color.RED
                        pokemon.vidaActual < pokemon.vidaMax * 0.5 -> Color.YELLOW
                        else -> Color.GREEN
                    }
                )
            }
            val image1 = pokemon.obtenerImagenTipo1()
            if (image1 != null)
                binding.ivTipo1.setImageResource(image1)
            else
                binding.ivTipo1.setImageDrawable(null)

            val image2 = pokemon.obtenerImagenTipo2()
            if (image2 != null)
                binding.ivTipo2.setImageResource(image2)
            else
                binding.ivTipo2.setImageDrawable(null)
        } else {
            Toast.makeText(this, "No se ha recibido ningún Pokémon", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}