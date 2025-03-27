package com.josh.obesityapp.utils

// Utility function to transform Sanity.io image URL
fun transformSanityImageUrl(
    url: String,
    width: Int = 150,
    height: Int? = 100
): String {
    return if (url.contains("cdn.sanity.io")) {
        val baseUrl = url.substringBeforeLast("-")
        val sizeParam = if (height != null) {
            "$width x $height"
        } else {
            "$width x $width"
        }
        "$baseUrl-$sizeParam.png?auto=format,compress&fit=max"
    } else {
        url
    }
}