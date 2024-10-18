package com.example.assesmenttask.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.assesmenttask.databinding.SendMoneyDetailsScreenBinding
import com.example.assesmenttask.handler.convertNumberToWords

class SendMoneyActivity : AppCompatActivity() {


    private var binding : SendMoneyDetailsScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SendMoneyDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpUi()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpUi(){
        binding?.uiMCvOneHundred?.setOnClickListener {
            binding?.uiEtEnterAmount?.setText("100")
            binding?.uiTvOnlyEnterAmount?.text = "Rupees ${convertNumberToWords(100)} only"
        }
        binding?.uiMcvFiveHundred?.setOnClickListener {
            binding?.uiEtEnterAmount?.setText("500")
            binding?.uiTvOnlyEnterAmount?.text = "Rupees ${convertNumberToWords(500)} only"
        }
        binding?.uiMcvOneThousand?.setOnClickListener {
            binding?.uiEtEnterAmount?.setText("1000")
            binding?.uiTvOnlyEnterAmount?.text = "Rupees ${convertNumberToWords(1000)} only"

        }
        binding?.uiMcvTwoThousand?.setOnClickListener {
            binding?.uiEtEnterAmount?.setText("2000")
            binding?.uiTvOnlyEnterAmount?.text = "Rupees ${convertNumberToWords(2000)} only"

        }
        binding?.uiEtEnterAmount?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val amountString = s.toString()
                val amount = amountString.toIntOrNull() ?: 0
                binding?.uiTvOnlyEnterAmount?.text = "Rupees ${convertNumberToWords(amount)} only"
            }
        })

        binding?.uiIvBackArrow?.setOnClickListener {
            finish()
        }

        binding?.uiCvProceedPayment?.setOnClickListener{
            val intent = Intent(this, UpiActivity :: class.java)
            startActivity(intent)
        }

    }

}