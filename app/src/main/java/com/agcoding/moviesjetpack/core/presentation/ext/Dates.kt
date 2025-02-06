package com.agcoding.moviesjetpack.core.presentation.ext

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

const val date_input_format = "yyyy-MM-dd"
const val date_output_format = "dd MMMM yy"

fun String.mapToDate(): String {
    if (isEmpty()) return ""
    val inputFormat = DateTimeFormatter.ofPattern(date_input_format, Locale.getDefault())
    val outputFormat = DateTimeFormatter.ofPattern(date_output_format, Locale.getDefault())

    val date = LocalDate.parse(this, inputFormat)
    return date.format(outputFormat)
}