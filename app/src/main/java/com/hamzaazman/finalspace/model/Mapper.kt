package com.hamzaazman.finalspace.util

import com.hamzaazman.finalspace.model.Character
import com.hamzaazman.finalspace.model.Episode
import com.hamzaazman.finalspace.model.Location
import com.hamzaazman.finalspace.model.Quote

const val tag_unknown = "Unknown"

fun fromCharacterToModel(character: Character): Character {
    return Character(
        id = character.id,
        name = character.name ?: tag_unknown,
        species = character.species ?: tag_unknown,
        origin = character.origin ?: tag_unknown,
        hair = character.hair ?: tag_unknown,
        img_url = character.img_url ?: "",
        gender = character.gender ?: tag_unknown,
        status = character.status ?: tag_unknown,
    )
}

fun fromLocationToModel(location: Location): Location {
    return Location(
        id = location.id,
        name = location.name ?: "",
        img_url = location.img_url ?: "",
        type = location.type ?: tag_unknown,
        notable_residents = location.notable_residents ?: emptyList(),
    )
}

fun fromEpisodeToModel(episode: Episode): Episode {
    return Episode(
        id = episode.id,
        name = episode.name ?: "",
        img_url = episode.img_url ?: "",
        air_date = episode.air_date ?: "",
        characters = episode.characters ?: emptyList(),
        director = episode.director ?: tag_unknown,
        writer = episode.writer ?: tag_unknown
    )
}

fun fromQuoteToModel(quote: Quote): Quote {
    return Quote(
        id = quote.id,
        quote = quote.quote ?: "",
        image = quote.image ?: "",
        by = quote.by ?: tag_unknown,
        character = quote.character ?: tag_unknown
    )
}