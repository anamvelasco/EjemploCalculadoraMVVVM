package com.ana.ejemplocalculadoramvvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ana.ejemplocalculadoramvvvm.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Base_Theme_EjemploCalculadoraMVVVM)
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Se hacen los observers para cada operación
        val sumaObserver = Observer<Double> { resultado ->
            mainBinding.resutTextView.setText(resultado.toString())
        }
        val restaObserver = Observer<Double> { resultado ->
            mainBinding.resutTextView.setText(resultado.toString())
        }
        val multiplicacionObserver = Observer<Double> { resultado ->
            mainBinding.resutTextView.setText(resultado.toString())
        }
        val divisionObserver = Observer<Double> { resultado ->
            mainBinding.resutTextView.setText(resultado.toString())
        }

        // Observers para mensajes de error
        val errorMsgObserver = Observer<String> { errorMsg ->
            Snackbar.make(view, errorMsg, Snackbar.LENGTH_INDEFINITE)
                .setAction("Aceptar") {}
                .show()
        }

        mainViewModel.suma.observe(this, sumaObserver)
        mainViewModel.resta.observe(this, restaObserver)
        mainViewModel.multiplicacion.observe(this, multiplicacionObserver)
        mainViewModel.division.observe(this, divisionObserver)
        mainViewModel.errorMsg.observe(this, errorMsgObserver)

        mainBinding.addButton.setOnClickListener {
            mainViewModel.validateNumbers(
                mainBinding.firstNumberEditText.text.toString(),
                mainBinding.secondNumberEditText.text.toString(),
                "+"
            )
        }

        mainBinding.substractButton.setOnClickListener {
            mainViewModel.validateNumbers(
                mainBinding.firstNumberEditText.text.toString(),
                mainBinding.secondNumberEditText.text.toString(),
                "-"
            )
        }

        mainBinding.multiplyButton.setOnClickListener {
            mainViewModel.validateNumbers(
                mainBinding.firstNumberEditText.text.toString(),
                mainBinding.secondNumberEditText.text.toString(),
                "*"
            )
        }

        mainBinding.divideButton.setOnClickListener {
            mainViewModel.validateNumbers(
                mainBinding.firstNumberEditText.text.toString(),
                mainBinding.secondNumberEditText.text.toString(),
                "/"
            )
        }
    }
}
