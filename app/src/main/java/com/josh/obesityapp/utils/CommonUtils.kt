package com.josh.obesityapp.utils

fun transformSanityImageUrl(url: String, width: Int = 300): String {
    return url.replace(
        "-${width}x${width}", // adjust based on your needs
        "?w=$width&fit=max&auto=format"
    )
}