package com.example.assesmenttask.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assesmenttask.databinding.NewAddBankScreenBinding
import com.example.assesmenttask.databinding.SelectBankAccountBinding
import com.example.assesmenttask.ui.VerifyDeviceActivity

class AddAccountActivity : AppCompatActivity() {

    private var binding : NewAddBankScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewAddBankScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.uiCvAddBankAccount?.setOnClickListener {
            val intent = Intent(this, VerifyDeviceActivity :: class.java)
            startActivity(intent)
        }

    }
}