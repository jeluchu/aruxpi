package com.jeluchu.aruxpi.models.jikax

import com.jeluchu.jikax.models.anime.AnimeData
import com.jeluchu.jikax.models.character.CharacterInfo
import com.jeluchu.jikax.models.staff.StaffInfo

data class AnimeFullData(
    /**
     * General info of anime.
     * @see AnimeData
     */
    val anime: AnimeData = AnimeData(),

    /**
     * Staff list of anime..
     */
    val staff: List<StaffInfo> = emptyList(),

    /**
     * Characters list of anime.
     */
    val characters: List<CharacterInfo> = emptyList()
)