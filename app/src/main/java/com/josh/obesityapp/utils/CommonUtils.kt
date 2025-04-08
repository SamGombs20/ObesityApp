package com.josh.obesityapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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
// Helper function to format date
@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(dateStr: String): String {
    return try {
        val instant = Instant.parse(dateStr)
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy").withZone(ZoneId.systemDefault())
        formatter.format(instant)
    } catch (e: Exception) {
        "Unknown date"
    }
}
fun formatObesityLevel(level: String): String {
    return when (level) {
        "Insufficient_Weight"->"Insufficient Weight"
        "Normal_Weight"->"Normal Weight"
        "Overweight_Level_I"->"Overweight Level I"
        "Overweight_Level_II"->"Overweight Level II"
        "Obesity_Type_I"->"Obesity Type I"
        "Obesity_Type_II"->"Obesity Type II"
        "Obesity_Type_III"->"Obesity Type III"
        else -> "Unknown"
    }
}