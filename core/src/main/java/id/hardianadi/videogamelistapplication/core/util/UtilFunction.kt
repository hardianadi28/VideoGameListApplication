package id.hardianadi.videogamelistapplication.core.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 02/10/2020
 */
object UtilFunction {

    fun stringDateFormat(date: String?): String {
        val inputDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date!!)
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)

        return formatter.format(inputDate!!)
    }
}