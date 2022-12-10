package com.djr.tabnews.core.database

import com.djr.tabnews.core.database.entities.OfflinePostEntity
import com.djr.tabnews.core.database.entities.TagEntity
import com.djr.tabnews.core.models.posts.OfflinePostModel
import com.djr.tabnews.core.models.tags.TagModel

fun OfflinePostEntity.toModel(): OfflinePostModel = OfflinePostModel(
    id = id,
    ownerId = ownerId,
    slug = slug,
    title = title,
    ownerUsername = ownerUsername,
    createdAt = createdAt
)

fun OfflinePostModel.toEntity(): OfflinePostEntity = OfflinePostEntity(
    id = id,
    ownerId = ownerId,
    slug = slug,
    title = title,
    ownerUsername = ownerUsername,
    createdAt = createdAt
)

fun TagModel.toEntity(): TagEntity = TagEntity(
    tagName = tagName
)

fun TagEntity.toModel(): TagModel = TagModel(
    tagName = tagName
)