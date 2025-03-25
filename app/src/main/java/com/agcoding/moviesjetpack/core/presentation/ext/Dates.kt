package com.agcoding.moviesjetpack.core.presentation.ext

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

private const val date_input_format = "yyyy-MM-dd"
private const val date_output_format = "dd MMMM yy"

fun String.mapToDate(): String {
    if (isEmpty()) return ""
    val inputFormat = DateTimeFormatter.ofPattern(date_input_format, Locale.getDefault())
    val outputFormat = DateTimeFormatter.ofPattern(date_output_format, Locale.getDefault())

    val date = LocalDate.parse(this, inputFormat)
    return date.format(outputFormat)
}

fun String.toFormattedDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yy", Locale.getDefault())

        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(this)

        outputFormat.timeZone = TimeZone.getDefault()
        outputFormat.format(date ?: Date())
    } catch (e: Exception) {
        this
    }
}