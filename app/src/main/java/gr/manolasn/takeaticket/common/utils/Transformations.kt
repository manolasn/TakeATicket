package gr.manolasn.takeaticket.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import gr.manolasn.takeaticket.BuildConfig
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Transformations {

    fun backdropToImageURL(backdrop: String): String = BuildConfig.IMAGE_URL + backdrop

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
        return String.format(Locale.ENGLISH,"%.2f", value)
    }
}
