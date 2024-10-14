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

data class LocalException(
    val message: String
)