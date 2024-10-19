package com.example.assesmenttask.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.assesmenttask.databinding.UpiScreenBinding
import com.example.assesmenttask.ui.profile.AddAccountActivity

class UpiActivity : AppCompatActivity() {


    private var binding : UpiScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UpiScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setListener()

        initFocus()
    }

    private fun setListener(){
        binding?.frameLayout?.setOnClickListener {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding?.frameLayout?.windowToken,0)
        }

        setTextChangeListener(fromEditText = binding?.uiEtOne, targetEditText = binding?.uiEtTwo)
        setTextChangeListener(fromEditText = binding?.uiEtTwo, targetEditText = binding?.uiEtThree)
        setTextChangeListener(fromEditText = binding?.uiEtThree, targetEditText = binding?.uiEtFour)
        setTextChangeListener(fromEditText = binding?.uiEtFour, targetEditText = binding?.uiEtFive)
        setTextChangeListener(fromEditText = binding?.uiEtFive, targetEditText = binding?.uiEtSix)
        setTextChangeListener(fromEditText = binding?.uiEtSix, done = {
            val intent = Intent(this, SelectYourBankActivity :: class.java)
            startActivity(intent)
            reset()


        })

        setKeyListener(fromEditText = binding?.uiEtTwo, backToEditText = binding?.uiEtOne)
        setKeyListener(fromEditText = binding?.uiEtThree, backToEditText = binding?.uiEtTwo)
        setKeyListener(fromEditText = binding?.uiEtFour, backToEditText = binding?.uiEtThree)
        setKeyListener(fromEditText = binding?.uiEtFive, backToEditText = binding?.uiEtFour)
        setKeyListener(fromEditText = binding?.uiEtSix, backToEditText = binding?.uiEtFive)
    }

    private fun initFocus(){
        binding?.uiEtOne?.isEnabled = true

        binding?.uiEtOne?.postDelayed({
            binding?.uiEtOne?.requestFocus()
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(binding?.uiEtOne,InputMethodManager.SHOW_FORCED)
        },500)
    }

    private fun reset(){
        binding?.uiEtOne?.isEnabled = false
        binding?.uiEtTwo?.isEnabled = false
        binding?.uiEtThree?.isEnabled = false
        binding?.uiEtFour?.isEnabled = false
        binding?.uiEtFive?.isEnabled = false
        binding?.uiEtSix?.isEnabled = false

        binding?.uiEtOne?.setText("")
        binding?.uiEtTwo?.setText("")
        binding?.uiEtThree?.setText("")
        binding?.uiEtFour?.setText("")
        binding?.uiEtFive?.setText("")
        binding?.uiEtSix?.setText("")

        initFocus()
    }

    private fun setTextChangeListener(
        fromEditText : EditText?,
        targetEditText: EditText? = null,
        done : (() -> Unit)? = null,
    ){

        fromEditText?.addTextChangedListener{
            it?.let { string ->
                if (string.isNotEmpty()){
                    targetEditText?.let { editText ->
                        editText.isEnabled = true
                        editText.requestFocus()
                    } ?:run {
                        done?.let { done ->
                            done()
                        }
                    }
                    fromEditText.clearFocus()
                    fromEditText.isEnabled = false
                }

            }
        }

    }

    private fun setKeyListener(fromEditText: EditText?,backToEditText: EditText?){
        fromEditText?.setOnKeyListener{ _,keyCode,event ->


            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && fromEditText.text.isEmpty()){
                backToEditText?.isEnabled = true
                backToEditText?.requestFocus()
                backToEditText?.setText("")

                fromEditText.clearFocus()
                fromEditText.isEnabled =false
            }

            false

        }
    }
}




//media store concept

/*
private fun takeScreenshot(): Bitmap {
    val rootView: View = window.decorView.rootView
    val screenshot = Bitmap.createBitmap(rootView.width, rootView.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(screenshot)
    rootView.draw(canvas)
    return screenshot
}*/


/*private fun saveScreenshotToMediaStore(bitmap: Bitmap): Uri? {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "screenshot_${System.currentTimeMillis()}.png")
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/Screenshots")
    }

    val resolver = contentResolver
    val uri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    uri?.let {
        try {
            val outputStream = resolver.openOutputStream(it)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream?.flush()
            outputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    return uri
}*/

/*private fun shareScreenshot(uri: Uri) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "image/png"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)  // Grant permission to other apps to read the URI
    }

    startActivity(Intent.createChooser(intent, "Share Screenshot"))
}*/

/*private fun takeScreenshotAndShare() {
    val bitmap = takeScreenshot()
    val uri = saveScreenshotToMediaStore(bitmap)

    if (uri != null) {
        shareScreenshot(uri)
    } else {
        Toast.makeText(this, "Failed to save screenshot", Toast.LENGTH_SHORT).show()
    }
}*/
/*
val screenshotButton: Button = findViewById(R.id.screenshotButton)
screenshotButton.setOnClickListener {
    takeScreenshotAndShare()
}*/


