package com.josh.obesityapp.data.model

import com.google.gson.annotations.SerializedName

data class BlogType(
    @SerializedName("_id")
    val blogId: String,
    val title: String,
    val slug: Slug,
    val body: List<BodyBlock>,
    val mainImage: Image?, // Updated to match resolved reference
    val author: Author, // Updated to match resolved reference
    val publishedAt: String
)

data class Slug(val current: String)

data class BodyBlock(val children: List<TextBlock>)
data class TextBlock(
    val text: String,
    val marks: List<String>? = null, // Holds formatting like "strong", "link"
    val markDefs: List<MarkDef>? = null // Holds link definitions
)

data class MarkDef(
    val _key: String,
    val href: String // Link URL
)
data class Image(val asset: Asset)
data class Asset(val url: String)

data class Author(
    @SerializedName("_id")
    val authorId: String,
    val name: String,
    val bio: String
)

// Wrapper for Sanity API Response
data class SanityResponse(val result: List<BlogType>)
