package com.ana.ejemplocalculadoramvvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ana.ejemplocalculadoramvvvm.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val sumaObserver = Observer<Double> {suma ->
            mainBinding.resutTextView.setText(suma.toString())
        }

        mainViewModel.suma.observe(this, sumaObserver)

        val errorMsgObserver = Observer<String> {errorMsg ->
            Snackbar.make(view, errorMsg, Snackbar.LENGTH_INDEFINITE)
                .setAction("Aceptar"){}
                .show()
        }
        mainViewModel.errorMsg.observe(this,errorMsgObserver)
        mainBinding.addButton.setOnClickListener {
            mainViewModel.validateNumbers(mainBinding.firstNumberEditText.text.toString(), mainBinding.secondNumberEditText.text.toString())
           /* if (mainBinding.firstNumberEditText.text.toString().isEmpty() || mainBinding.secondNumberEditText.text.toString().isEmpty()){
                //Snackbar.make(view,"Debe digitar dos números", Snackbar.LENGTH_SHORT).show()
                Snackbar.make(view,"Debe digitar dos números", Snackbar.LENGTH_SHORT)
                    .setAction("Aceptar"){ Log.d("click", "OK")}
                    .show()
            } else {
                val number1 = mainBinding.firstNumberEditText.text.toString().toDouble()
                val number2 = mainBinding.secondNumberEditText.text.toString().toDouble()
                val total = number1 + number2
                mainBinding.resutTextView.setText(total.toString())
                //mainBinding.resutTextView.text = total.toString()
            }*/


        }
    }
}