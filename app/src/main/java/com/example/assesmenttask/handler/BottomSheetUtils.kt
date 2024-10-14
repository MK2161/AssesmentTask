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
    onClickAction: () -> Unit,
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