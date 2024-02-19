package com.jeluchu.aruxpi.core.extensions

import com.jeluchu.aruxpi.core.utils.empty
import com.jeluchu.aruxpi.models.anime.Actor
import com.jeluchu.aruxpi.models.anime.Character
import com.jeluchu.aruxpi.models.anime.Episode
import com.jeluchu.aruxpi.models.anime.Individual
import com.jeluchu.aruxpi.models.anime.Staff
import com.jeluchu.jikax.models.character.CharacterInfo
import com.jeluchu.jikax.models.character.VoiceActor
import com.jeluchu.jikax.models.staff.Person
import com.jeluchu.jikax.models.staff.StaffInfo
import com.jeluchu.monkx.models.anime.AnimeEpisode

fun AnimeEpisode.toEpisode() = Episode(
    id = id,
    number = number,
    nextEpisodeDate = String.empty()
)

fun com.jeluchu.tioxime.models.anime.AnimeEpisode.toEpisode() = Episode(
    id = id,
    number = number,
    nextEpisodeDate = String.empty()
)

fun StaffInfo.toStaff() = Staff(
    person = person.toIndividual(),
    positions = positions
)

fun CharacterInfo.toCharacter() = Character(
    character = character.toIndividual(),
    role = role,
    voiceActor = voiceActor.map { it.toActor() }
)

fun Person.toIndividual() = Individual(
    malId = malId,
    url = url,
    name = name,
    images = images.jpg.generic
)

fun VoiceActor.toActor() = Actor(
    person = person.toIndividual(),
    language = language
)