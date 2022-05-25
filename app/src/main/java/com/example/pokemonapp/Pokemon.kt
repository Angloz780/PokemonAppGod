package com.example.pokemonapp

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Pokemon (
    val abilities: List<Ability>,

    @SerializedName("base_experience")
    val baseExperience: Long,

    val forms: List<Species>,

    @SerializedName("game_indices")
    val gameIndices: List<GameIndex>,

    val height: Long,

    @SerializedName("held_items")
    val heldItems: List<HeldItem>,

    val id: Long,

    @SerializedName("is_default")
    val isDefault: Boolean,

    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,

    //val moves: List<Move>,
    val name: String,
    val order: Long,


    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Long
) {

    var vidaMax = 200
    var vidaActual = vidaMax

    fun iniciarVida(){

        vidaMax = (150..250).random()
        vidaActual = (1..vidaMax).random()

    }
    fun nameCapitalized() = "${name[0].uppercase()}${name.drop(1)}"

    /* Es lo mismo que arriba
    fun nameCapitalized() : String {
        return "${name[0].uppercase()}${name.drop(1)}"
    }*/

    companion object {
        fun fromJson(json: String): Pokemon {
            val gson = Gson()
            return gson.fromJson(json, Pokemon::class.java)
        }
    }


    fun toJson() : String {
        val gson = Gson()
        return gson.toJson(this)
    }

    fun decirNombreYTipo() : String {
        var result =  "$name es de tipo "
        types.forEach {
            result+="${it.type.name} "
        }
        result+="\n"
        return result
    }

    fun obtenerImagenTipo1(): Int? {
        if (types.isEmpty()) return null
        return getResource(types[0].type.name)
    }

    fun obtenerImagenTipo2(): Int? {
        if (types.isEmpty() || types.size == 1) return null
        return getResource(types[1].type.name)
    }

    private fun getResource(type: String): Int {
        return when {
            type.contentEquals("normal") -> R.mipmap.ic_normal
            type.contentEquals("fighting") -> R.mipmap.ic_lucha
            type.contentEquals("flying") -> R.mipmap.ic_volador
            type.contentEquals("poison") -> R.mipmap.ic_veneno
            type.contentEquals("ground") -> R.mipmap.ic_tierra
            type.contentEquals("rock") -> R.mipmap.ic_roca
            type.contentEquals("bug") -> R.mipmap.ic_bicho
            type.contentEquals("ghost") -> R.mipmap.ic_fantasma
            type.contentEquals("steel") -> R.mipmap.ic_acero
            type.contentEquals("fire") -> R.mipmap.ic_fuego
            type.contentEquals("water") -> R.mipmap.ic_agua
            type.contentEquals("grass") -> R.mipmap.ic_planta
            type.contentEquals("electric") -> R.mipmap.ic_electrico
            type.contentEquals("psychic") -> R.mipmap.ic_psiquico
            type.contentEquals("ice") -> R.mipmap.ic_hielo
            type.contentEquals("dragon") -> R.mipmap.ic_dragon
            type.contentEquals("fairy") -> R.mipmap.ic_hada
            type.contentEquals("dark") -> R.mipmap.ic_siniestro
            type.contentEquals("unknown") -> R.mipmap.ic_desconocido

            else -> R.mipmap.ic_desconocido

        }
    }
}

@Serializable
data class Ability (
    val ability: Species,

    @SerializedName("is_hidden")
    val isHidden: Boolean,

    val slot: Long
)

@Serializable
data class Species (
    val name: String,
    val url: String
)

@Serializable
data class GameIndex (
    @SerializedName("game_index")
    val gameIndex: Long,

    val version: Species
)

@Serializable
data class HeldItem (
    val item: Species,

    @SerializedName("version_details")
    val versionDetails: List<VersionDetail>
)

@Serializable
data class VersionDetail (
    val rarity: Long,
    val version: Species
)

@Serializable
data class Move (
    val move: Species,

    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)

@Serializable
data class VersionGroupDetail (
    @SerializedName("level_learned_at")
    val levelLearnedAt: Long,

    @SerializedName("move_learn_method")
    val moveLearnMethod: Species,

    @SerializedName("version_group")
    val versionGroup: Species
)

@Serializable
data class GenerationV (
    @SerializedName("black-white")
    val blackWhite: Sprites
)

@Serializable
data class GenerationIv (
    @SerializedName("diamond-pearl")
    val diamondPearl: Sprites,

    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: Sprites,

    val platinum: Sprites
)

@Serializable
data class Versions (
    @SerializedName("generation-i")
    val generationI: GenerationI,

    @SerializedName("generation-ii")
    val generationIi: GenerationIi,

    @SerializedName("generation-iii")
    val generationIii: GenerationIii,

    @SerializedName("generation-iv")
    val generationIv: GenerationIv,

    @SerializedName("generation-v")
    val generationV: GenerationV,

    @SerializedName("generation-vi")
    val generationVi: Map<String, Home>,

    @SerializedName("generation-vii")
    val generationVii: GenerationVii,

    @SerializedName("generation-viii")
    val generationViii: GenerationViii
)

@Serializable
data class Sprites (
    @SerializedName("back_default")
    val backDefault: String,

    @SerializedName("back_female")
    val backFemale: String? = null,

    @SerializedName("back_shiny")
    val backShiny: String,

    @SerializedName("back_shiny_female")
    val backShinyFemale: String? = null,

    @SerializedName("front_default")
    val frontDefault: String,

    @SerializedName("front_female")
    val frontFemale: String? = null,

    @SerializedName("front_shiny")
    val frontShiny: String,

    @SerializedName("front_shiny_female")
    val frontShinyFemale: String? = null,

    //val other: Other? = null,
    //val versions: Versions? = null,
    //val animated: Sprites? = null
)

@Serializable
data class GenerationI (
    @SerializedName("red-blue")
    val redBlue: RedBlue,

    val yellow: RedBlue
)

@Serializable
data class RedBlue (
    @SerializedName("back_default")
    val backDefault: String,

    @SerializedName("back_gray")
    val backGray: String,

    @SerializedName("back_transparent")
    val backTransparent: String,

    @SerializedName("front_default")
    val frontDefault: String,

    @SerializedName("front_gray")
    val frontGray: String,

    @SerializedName("front_transparent")
    val frontTransparent: String
)

@Serializable
data class GenerationIi (
    val crystal: Crystal,
    val gold: Gold,
    val silver: Gold
)

@Serializable
data class Crystal (
    @SerializedName("back_default")
    val backDefault: String,

    @SerializedName("back_shiny")
    val backShiny: String,

    @SerializedName("back_shiny_transparent")
    val backShinyTransparent: String,

    @SerializedName("back_transparent")
    val backTransparent: String,

    @SerializedName("front_default")
    val frontDefault: String,

    @SerializedName("front_shiny")
    val frontShiny: String,

    @SerializedName("front_shiny_transparent")
    val frontShinyTransparent: String,

    @SerializedName("front_transparent")
    val frontTransparent: String
)

@Serializable
data class Gold (
    @SerializedName("back_default")
    val backDefault: String,

    @SerializedName("back_shiny")
    val backShiny: String,

    @SerializedName("front_default")
    val frontDefault: String,

    @SerializedName("front_shiny")
    val frontShiny: String,

    @SerializedName("front_transparent")
    val frontTransparent: String? = null
)

@Serializable
data class GenerationIii (
    val emerald: Emerald,

    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: Gold,

    @SerializedName("ruby-sapphire")
    val rubySapphire: Gold
)

@Serializable
data class Emerald (
    @SerializedName("front_default")
    val frontDefault: String,

    @SerializedName("front_shiny")
    val frontShiny: String
)

@Serializable
data class Home (
    @SerializedName("front_default")
    val frontDefault: String,

    @SerializedName("front_female")
    val frontFemale: String? = null,

    @SerializedName("front_shiny")
    val frontShiny: String,

    @SerializedName("front_shiny_female")
    val frontShinyFemale: String? = null
)

@Serializable
data class GenerationVii (
    val icons: DreamWorld,

    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: Home
)

@Serializable
data class DreamWorld (
    @SerializedName("front_default")
    val frontDefault: String,

    @SerializedName("front_female")
    val frontFemale: String? = null
)

@Serializable
data class GenerationViii (
    val icons: DreamWorld
)

@Serializable
data class Other (
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld,

    val home: Home,

    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)

@Serializable
data class OfficialArtwork (
    @SerializedName("front_default")
    val frontDefault: String
)

@Serializable
data class Stat (
    @SerializedName("base_stat")
    val baseStat: Long,

    val effort: Long,
    val stat: Species
)

@Serializable
data class Type (
    val slot: Long,
    val type: Species
)
