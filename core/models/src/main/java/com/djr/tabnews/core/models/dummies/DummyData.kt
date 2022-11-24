package com.djr.tabnews.core.models.dummies

import com.djr.tabnews.core.models.posts.PostContent
import com.djr.tabnews.core.models.posts.PostModel

val DUMMY_POST = PostModel(
    id = "54ba5af4-0ac6-4880-9f94-c635af938276",
    slug = "tentando-construir-um-pedaco-de-internet-mais-massa",
    ownerId = "54ba5af4-0ac6-4880-9f94-c635af938276",
    title = "Quais foram os maiores obstáculos que vocês já passaram como programadores(as)?",
    publishedAt = "2022-10-28T22:23:02.042Z",
    ownerUsername = "danielj06",
    tabcoins = 35000,
    commentsAmount = 32
)

val DUMMY_POST_CONTENT = PostContent(
    id = "54ba5af4-0ac6-4880-9f94-c635af938276",
    ownerId = "54ba5af4-0ac6-4880-9f94-c635af938276",
    title = "Tentando construir um pedaço de internet mais massa",
    publishedAt = "2022-10-28T22:23:02.042Z",
    ownerUsername = "danielj06",
    tabcoins = 3,
    comments = 32,
    body = "Não sei se você compartilha comigo a visão de que recentemente a **internet deixou de ser um lugar massa**, pelo menos em sites onde a mídia principal é primariamente `texto`.",
    slug = "tentando-construir-um-pedaco-de-internet-mais-massa"
)

val DUMMY_POSTS = listOf(
    DUMMY_POST,
    DUMMY_POST,
    DUMMY_POST,
    DUMMY_POST,
    DUMMY_POST
)