package com.example.assesmenttask.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assesmenttask.databinding.LoadingBankAccountScreenBinding
import com.example.assesmenttask.databinding.NewAddBankScreenBinding

class AccountLoadingScreen : AppCompatActivity() {

    private var binding : LoadingBankAccountScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoadingBankAccountScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.uiIvBackArrow?.setOnClickListener {
            finish()
        }


    }
}