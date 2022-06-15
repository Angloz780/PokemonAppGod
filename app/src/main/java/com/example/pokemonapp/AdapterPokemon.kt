package com.example.pokemonapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class AdapterPokemon(private val callback: MainActivity) : RecyclerView.Adapter<AdapterPokemon.PokemonViewHolder>() {

    class PokemonViewHolder(val pokemonBinding: ItemPokemonBinding) : RecyclerView.ViewHolder(pokemonBinding.root)

    private var pokemons = ListaPokemon()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val pokemonBinding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(pokemonBinding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons.listaPokemon[position]
        holder.pokemonBinding.tvPokemon.text = pokemon.nameCapitalized()
        Picasso.get().load(pokemon.sprites.frontDefault).into(holder.pokemonBinding.ivPokemon)
        val image1 = pokemon.obtenerImagenTipo1()
        if (image1 != null)
            holder.pokemonBinding.ivTipo1.setImageResource(image1)
        else
            holder.pokemonBinding.ivTipo1.setImageDrawable(null)
        val image2 = pokemon.obtenerImagenTipo2()
        if (image2 != null)
            holder.pokemonBinding.ivTipo2.setImageResource(image2)
        else
            holder.pokemonBinding.ivTipo2.setImageDrawable(null)

        holder.pokemonBinding.root.setOnClickListener {
            PokemonActivity.start(pokemon, holder.pokemonBinding.root.context)
        }

        holder.pokemonBinding.root.setOnLongClickListener {
            callback.pokemonFavoritoSelected(pokemon)
            true
        }

        holder.pokemonBinding.pbVida.apply {
            max = pokemon.vidaMax
            progress = pokemon.vidaActual
            progressTintList = ColorStateList.valueOf(
                when {
                    pokemon.vidaActual < pokemon.vidaMax * 0.15 -> Color.RED
                    pokemon.vidaActual < pokemon.vidaMax * 0.5 -> Color.YELLOW
                    else -> Color.GREEN
                }
            )
        }
    }

    override fun getItemCount(): Int {
        return pokemons.listaPokemon.size
    }

    fun actualizarLista(listaPokemon: ListaPokemon) {
        pokemons = listaPokemon
        notifyDataSetChanged()
    }
}