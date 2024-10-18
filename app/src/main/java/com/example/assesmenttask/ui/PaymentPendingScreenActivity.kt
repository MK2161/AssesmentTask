package com.example.assesmenttask.ui

import android.Manifest
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.telephony.TelephonyManager
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.assesmenttask.R
import com.example.assesmenttask.databinding.PaymentPendingScreenBinding
import com.example.assesmenttask.databinding.TransferDetailsScreenBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PaymentPendingScreenActivity : AppCompatActivity() {


    private lateinit var circularProgressBar: ProgressBar
    private var progressStatus = 0
    private var binding : TransferDetailsScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TransferDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
       // setUpUi()
        binding?.uiBtnViewHistory?.setOnClickListener {
            requestStoragePermission()
        }
        binding?.uiBtnShareReceipt?.setOnClickListener {

        }


    }



    private fun setUpUi(){

       binding?.uiBtnShareReceipt?.setOnClickListener {

           val intent = Intent()
           intent.action = Intent.ACTION_SEND
           intent.type = "text/plain"
           val shareIntent = Intent.createChooser(intent,"Send To: ")
           startActivity(shareIntent)

       }

    }

  /*  private fun takeScreenshot() {
        try {
            // Get root view of the activity
            val rootView: View = window.decorView.rootView

            // Create a Bitmap of the same size as the view
            val bitmap = Bitmap.createBitmap(
                rootView.width,
                rootView.height,
                Bitmap.Config.ARGB_8888
            )

            // Bind a Canvas to the bitmap and draw the view onto the canvas
            val canvas = Canvas(bitmap)
            rootView.draw(canvas)

            // Save the bitmap as a file
            saveBitmap(bitmap)

            Toast.makeText(this, "Screenshot taken", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Log.e("Screenshot Error", "Failed to capture screenshot", e)
        }
    }*/

    /*private fun saveBitmap(bitmap: Bitmap) {
        // Create a directory to save the screenshot
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val file = File(path, "screenshot_${System.currentTimeMillis()}.png")

        try {
            // Write the bitmap to the file
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()

            Toast.makeText(this, "Screenshot saved at: ${file.absolutePath}", Toast.LENGTH_LONG).show()

        } catch (e: IOException) {
            Log.e("Screenshot Save Error", "Failed to save screenshot", e)
        }
    }*/

    private fun takeScreenshotAndShare() {
        val bitmap = takeScreenshot()
        val view = findViewById<MaterialCardView>(R.id.uiMcvTransferDetails)
        val file = saveViewScreenshot(view)

        if (file != null) {
            shareScreenshot(file)
        } else {
            Toast.makeText(this, "Screenshot failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        } else {
            takeScreenshotAndShare()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takeScreenshotAndShare()
        } else {
            Toast.makeText(this, "Storage permission is required", Toast.LENGTH_SHORT).show()
        }
    }

    private fun takeScreenshot(): Bitmap {
        val rootView: View = window.decorView.rootView
        val screenshot = Bitmap.createBitmap(rootView.width, rootView.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(screenshot)
        rootView.draw(canvas)
        return screenshot
    }

    private fun captureViewScreenshot(view: View): Bitmap {
        // Create a bitmap with the same size as the view
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        // Bind a canvas to the bitmap
        val canvas = Canvas(bitmap)

        // Draw the view on the canvas (which draws on the bitmap)
        view.draw(canvas)

        return bitmap
    }

    private fun saveViewScreenshot(view: View): File? {
        // Capture the view screenshot as a Bitmap
        val bitmap = captureViewScreenshot(view)

        // Save the bitmap as an image file
        val directory = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Screenshots")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val fileName = "screenshot_${System.currentTimeMillis()}.png"
        val file = File(directory, fileName)

        try {
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
            return file
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }


    private fun saveScreenshot(bitmap: Bitmap): File? {
        val directory = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Screenshots")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val fileName = "screenshot_${System.currentTimeMillis()}.png"
        val file = File(directory, fileName)

        try {
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
            return file
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }


    private fun shareScreenshot(file: File) {
        val uri: Uri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.fileprovider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        startActivity(Intent.createChooser(intent, "Share Screenshot"))
    }
}