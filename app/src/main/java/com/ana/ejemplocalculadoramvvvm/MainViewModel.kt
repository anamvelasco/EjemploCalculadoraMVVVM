package com.ana.ejemplocalculadoramvvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    //Opcion 1
    val suma : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    //Opcion 2
    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

    fun validateNumbers(number1: String, number2: String) {
        if (number1.isEmpty() || number2.isEmpty()) {
            _errorMsg.value = "Debe digitar todos los números"
        } else {
            suma.value = number1.toDouble() + number2.toDouble()
        }
    }

}