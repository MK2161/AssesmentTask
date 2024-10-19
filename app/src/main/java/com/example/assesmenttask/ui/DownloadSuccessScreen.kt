package com.example.assesmenttask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assesmenttask.databinding.StatementDownloadScreenBinding
import com.example.assesmenttask.handler.statementBottomSheet

class DownloadSuccessScreen : AppCompatActivity() {

    private var binding : StatementDownloadScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StatementDownloadScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.uiBtnViewStatement?.setOnClickListener {
            statementBottomSheet(this)
        }
    }
}