package com.example.assesmenttask.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.assesmenttask.databinding.UpiScreenBinding

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
            Toast.makeText(this,"successfulll",Toast.LENGTH_SHORT).show()
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
        fromEditText?.setOnKeyListener{ _,_,event ->


            if (null != event && KeyEvent.KEYCODE_DEL == event.keyCode){
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