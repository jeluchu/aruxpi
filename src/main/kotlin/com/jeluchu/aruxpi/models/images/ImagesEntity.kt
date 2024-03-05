package com.jeluchu.aruxpi.models.images

import com.jeluchu.aruxpi.core.extensions.orZero
import com.jeluchu.qwanx.models.images.ImageQueryEntity
import com.jeluchu.qwanx.models.images.ResultEntity

data class ImagesEntity(
    val query: String,
    val images: List<ImageMediaEntity>
) {
    data class ImageMediaEntity(
        val media: String,
        val thumbnail: String,
        val width: Int,
        val height: Int,
        val url: String
    )
}

fun ImageQueryEntity.toImagesEntity(
    query: String
) = ImagesEntity(
    query = query,
    images = data.result.items?.map { it.toImagesEntity() }.orEmpty()
)

fun ResultEntity.ItemEntity.toImagesEntity() = ImagesEntity.ImageMediaEntity(
    media = media.orEmpty(),
    thumbnail = thumbnail.orEmpty(),
    width = width.orZero(),
    height = height.orZero(),
    url = url.orEmpty()
)