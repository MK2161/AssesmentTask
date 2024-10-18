package com.example.assesmenttask.handler

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.assesmenttask.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.util.Calendar

fun Context.showFaceBottomSheetDialog(
    context: Context
) {
    val bottomSheet = LayoutInflater.from(this).inflate(R.layout.filter_history_screen, null)
    val dialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
    dialog.setContentView(bottomSheet)
    dialog.show()
    val uiFromDate = bottomSheet.findViewById<AppCompatTextView>(R.id.uiTvFromDate)
    val uiToDate = bottomSheet.findViewById<AppCompatTextView>(R.id.uiTvToDate)
    val fromDate = bottomSheet.findViewById<MaterialCardView>(R.id.uiMcvFromDate)
    val toDate = bottomSheet.findViewById<MaterialCardView>(R.id.uiMcvToDate)
    fromDate.setOnClickListener {
        showDatePickerDialog(
            context = context,
            onClickAction = {
                uiFromDate.text = it
            }
        )
    }
    toDate.setOnClickListener {
        showDatePickerDialog(
            context = context,
            onClickAction = {
                uiToDate.text = it
            }
        )
    }
    val uiBtnCancel = bottomSheet.findViewById<MaterialButton>(R.id.uiBtnClearAll)

    bottomSheet.findViewById<ImageView>(R.id.uiIvClose).setOnClickListener {
        dialog.dismiss()
    }
    uiBtnCancel.setOnClickListener {
        dialog.dismiss()
    }

}

private fun showDatePickerDialog(
    context: Context,
    onClickAction: (String) -> Unit
) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)

    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)


    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day
            ->
            val selectedDate = "$day/${month + 1}/$year"
            onClickAction( selectedDate)
        },
        year,
        month,
        day
    )

    datePickerDialog.show()
}

fun convertNumberToWords(num: Int): String {
    if (num < 0 || num > 100000000) return ""

    val underTwenty = arrayOf(
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    )

    val tens = arrayOf(
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    )

    var result = ""
    var number = num


    if (number >= 10000000) {
        result += convertNumberToWords(number / 10000000) + " Crore "
        number %= 10000000
    }


    if (number >= 100000) {
        result += convertNumberToWords(number / 100000) + " Lakh "
        number %= 100000
    }


    if (number >= 1000) {
        result += convertNumberToWords(number / 1000) + " Thousand "
        number %= 1000
    }


    if (number >= 100) {
        result += underTwenty[number / 100] + " Hundred "
        number %= 100
    }


    if (number < 20) {
        result += underTwenty[number]
    } else {
        result += tens[number / 10]
        if (number % 10 != 0) {
            result += "-" + underTwenty[number % 10]
        }
    }

    return result.trim()
}



/*fun convertNumberToWords(num: Int): String {
    if (num == 0 || num > 10000) return ""

    val underTwenty = arrayOf(
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
        "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    )

    val tens = arrayOf(
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    )

    var result = ""

    if (num >= 1000) {
        result += underTwenty[num / 1000] + " Thousand "
    }

    if ((num % 1000) >= 100) {
        result += underTwenty[(num % 1000) / 100] + " Hundred "
    }

    val remainder = num % 100
    if (remainder < 20) {
        result += underTwenty[remainder]
    } else {
        result += tens[remainder / 10]
        if (remainder % 10 != 0) {
            result += "-" + underTwenty[remainder % 10]
        }
    }

    return result.trim()
}*/
