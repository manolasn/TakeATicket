package gr.manolasn.takeaticket.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import gr.manolasn.takeaticket.BuildConfig
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Transformations {

    fun backdropToImageURL(backdrop: String): String = BuildConfig.IMAGE_URL + backdrop

    fun backdropToFullImageURL(backdrop: String): String = BuildConfig.IMAGE_URL_FULL + backdrop

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateString(dateString: String): String {
        if (dateString.isEmpty()) return "-"
        // Parse the date string to LocalDate
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, inputFormatter)

        // Format the LocalDate to the desired output format
        val outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy")
        return date.format(outputFormatter)
    }

    fun formatDouble(value: Double): String {
        if (value == 0.0) return "-"
        return String.format(Locale.ENGLISH, "%.0f", value * 10)
    }

    fun idToIMDB(value: String): String {
        return "https://www.imdb.com/title/${value}/"
    }

    fun transformMinutesToHoursAndMinutes(minutes: Int): String {
        if (minutes == 0) return "-"
        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        return when {
            hours > 0 && remainingMinutes > 0 -> "$hours" + "h" + " $remainingMinutes" + "m"
            hours > 0 -> "$hours" + "h"
            else -> "$remainingMinutes" + "m"
        }
    }

    fun formatBigNumberWithCommas(number: Int): String {
        val formatter = NumberFormat.getNumberInstance(Locale.US)
        if (number == 0) return "-"
        return formatter.format(number) + " $"
    }


}
