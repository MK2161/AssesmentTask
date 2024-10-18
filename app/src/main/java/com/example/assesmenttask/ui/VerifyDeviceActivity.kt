package com.example.assesmenttask.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.assesmenttask.databinding.VerifyDeviceScreenBinding

class VerifyDeviceActivity :AppCompatActivity() {

    private var binding : VerifyDeviceScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VerifyDeviceScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED) {

            // Request permissions
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_PHONE_NUMBERS), 1)
        } else {
            // Permissions are already granted
            getPhoneNumberAndSlot()
        }

        binding?.roundCheckBox1?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding?.uiTvPhoneNumber?.text = binding?.uiTvPhoneNumber1?.text
                binding?.roundCheckBox2?.isChecked = false
            } else {
                binding?.uiTvPhoneNumber?.text = binding?.uiTvPhoneNumber2?.text
                binding?.roundCheckBox2?.isChecked = true
            }
        }
        binding?.roundCheckBox2?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding?.uiTvPhoneNumber?.text = binding?.uiTvPhoneNumber2?.text
                binding?.roundCheckBox1?.isChecked = false
            } else {
                binding?.uiTvPhoneNumber?.text = binding?.uiTvPhoneNumber1?.text
                binding?.roundCheckBox1?.isChecked = true
            }
        }

        binding?.uiCvSendSms?.setOnClickListener {
            val intent = Intent(this,AccountLoadingScreen::class.java)
            startActivity(intent)
        }
    }


    private fun getPhoneNumberAndSlot() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            val subscriptionManager = getSystemService(TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                val subscriptionInfoList = subscriptionManager.activeSubscriptionInfoList

                if (subscriptionInfoList != null && subscriptionInfoList.isNotEmpty()) {
                    for (subscriptionInfo in subscriptionInfoList) {
                        val simSlotIndex = subscriptionInfo.simSlotIndex
                        val subscriptionId = subscriptionInfo.subscriptionId

                        // Get TelephonyManager instance for this specific subscription
                        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                        val telephonyManagerForSubscription = telephonyManager.createForSubscriptionId(subscriptionId)

                        // Get phone number for this subscription (can be null)
                        val phoneNumber = telephonyManagerForSubscription.line1Number

                        if (simSlotIndex == 0){
                            binding?.uiTvPhoneNumber1?.text = phoneNumber
                        } else {
                            binding?.uiTvPhoneNumber2?.text = phoneNumber
                        }

                        Log.e("namesssssss","phone$simSlotIndex  $phoneNumber")

                        // Show SIM slot and phone number
                     //   Toast.makeText(this, "SIM Slot: $simSlotIndex, Phone Number: $phoneNumber", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "No active SIM cards found", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getPhoneNumberAndSlot()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
        }
    }
}