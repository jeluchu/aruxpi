package com.jeluchu.aruxpi.extractor.data

import com.jeluchu.aruxpi.core.enums.Sources
import com.jeluchu.aruxpi.core.extensions.orZero
import com.jeluchu.aruxpi.core.extensions.toAiringTime
import com.jeluchu.aruxpi.core.extensions.toAnimeBroadcast
import com.jeluchu.aruxpi.core.extensions.toCharacter
import com.jeluchu.aruxpi.core.extensions.toCompanies
import com.jeluchu.aruxpi.core.extensions.toEpisode
import com.jeluchu.aruxpi.core.extensions.toExternalLinks
import com.jeluchu.aruxpi.core.extensions.toMinutes
import com.jeluchu.aruxpi.core.extensions.toRelated
import com.jeluchu.aruxpi.core.extensions.toSeasons
import com.jeluchu.aruxpi.core.extensions.toStaff
import com.jeluchu.aruxpi.core.extensions.toThemes
import com.jeluchu.aruxpi.core.extensions.toType
import com.jeluchu.aruxpi.core.extensions.toVideoPromo
import com.jeluchu.aruxpi.models.anime.AiringTime.Companion.orEmpty
import com.jeluchu.aruxpi.models.anime.AnimeBroadcast.Companion.orEmpty
import com.jeluchu.aruxpi.models.anime.AnimeInfoEntity
import com.jeluchu.aruxpi.models.anime.Themes.Companion.orEmpty
import com.jeluchu.aruxpi.models.anime.VideoPromo.Companion.orEmpty
import com.jeluchu.aruxpi.models.images.ImagesEntity
import com.jeluchu.aruxpi.models.jikax.AnimeFullData
import com.jeluchu.monkx.models.anime.AnimeInfo
import java.util.Calendar

suspend fun toAnimeInfoData(
    name: String,
    jikaxInfo: AnimeFullData?,
    monkxInfo: AnimeInfo?,
    imagesInfo: ImagesEntity?,
): AnimeInfoEntity {
    jikaxInfo?.let { jikan ->
        jikan.anime?.let { jikax ->
            val info = AnimeInfoEntity(
                malId = jikax.malId.orZero(),
                title = jikax.titles?.first { it.type == "Default" }?.title.orEmpty(),
                score = jikax.score.orZero().toString(),
                staff = jikaxInfo.staff?.map { it.toStaff() }.orEmpty(),
                characters = jikaxInfo.characters?.map { it.toCharacter() }.orEmpty(),
                status = jikax.status.orEmpty(),
                type = jikax.type.toType(),
                url = jikax.url.orEmpty(),
                promo = jikax.trailer?.toVideoPromo().orEmpty(),
                source = jikax.source.orEmpty(),
                duration = jikax.duration?.toMinutes().orEmpty(),
                rank = jikax.rank.orZero(),
                titles = jikax.titles?.map { it.toAiringTime() }.orEmpty(),
                airing = jikax.airing ?: false,
                aired = jikax.aired?.toAiringTime().orEmpty(),
                broadcast = jikax.broadcast?.toAnimeBroadcast().orEmpty(),
                season = jikax.season.toSeasons(),
                year = jikax.year ?: Calendar.getInstance().weekYear,
                external = jikax.external?.map { it.toExternalLinks() }.orEmpty(),
                streaming = jikax.streaming?.map { it.toExternalLinks() }.orEmpty(),
                studios = jikax.studios?.map { it.toCompanies() }.orEmpty(),
                licensors = jikax.licensors?.map { it.toCompanies() }.orEmpty(),
                producers = jikax.producers?.map { it.toCompanies() }.orEmpty(),
                theme = jikax.theme?.toThemes().orEmpty(),
                relations = jikax.relations?.map { it.toRelated() }.orEmpty(),
                gallery = imagesInfo?.images.orEmpty()
            )

            monkxInfo?.let { monkx ->
                return info.copy(
                    poster = monkx.image,
                    cover = monkx.cover,
                    genres = monkx.genres,
                    synopsis = monkx.synopsis,
                    episodes = monkx.episodes.map { it.toEpisode() },
                    episodesCount = monkx.episodesCount,
                    episodeSource = Sources.Monkx
                )
            }

            name.getTioximeInfo()?.let { tioxime ->
                return info.copy(
                    poster = tioxime.image,
                    cover = tioxime.cover,
                    genres = tioxime.genres,
                    synopsis = tioxime.synopsis,
                    episodes = tioxime.episodes.map { it.toEpisode() },
                    episodesCount = tioxime.episodesCount,
                    episodeSource = Sources.Tioxime
                )
            }

            return info.copy(
                poster = jikax.images?.webp?.large.orEmpty(),
                cover = jikax.images?.webp?.large.orEmpty()
            )
        }
    }

    return AnimeInfoEntity(hasError = true)
}