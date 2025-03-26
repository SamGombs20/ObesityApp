package com.josh.obesityapp.data.model

import com.google.gson.annotations.SerializedName

data class Blog(
    @SerializedName("_id")
    val blogId:String,
    val title:String,
    val slug: Slug,
    val body:List<BodyBlock>,
    val mainImage: Image?,
    val author: Author,
    val publishedAt: String
)
data class Slug(val current: String)
data class BodyBlock(val children: List<TextBlock>)
data class TextBlock(val text: String)

data class Image(val asset: Asset)
data class Asset(val url: String)

data class Author(
    @SerializedName("_id")
    val authorId: String,
    val name: String,
    val bio: String
)