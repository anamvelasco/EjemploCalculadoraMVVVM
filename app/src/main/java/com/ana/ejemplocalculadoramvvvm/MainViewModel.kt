package com.ana.ejemplocalculadoramvvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    //Se hace el ViewModel para la suma
    val suma: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }
   //Se hace el ViewModel para la resta
    val resta: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    //Se hace el ViewModel para la Multiplicación
    val multiplicacion: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    //Se hace el ViewModel para la División
    val division: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

    fun validateNumbers(number1: String, number2: String, operation: String) {
        if (number1.isEmpty() || number2.isEmpty()) {
            _errorMsg.value = "Debe digitar todos los números"
        } else {
            //Se asignan las operaciones requeridas
            when (operation) {
                "+" -> suma.value = number1.toDouble() + number2.toDouble()
                "-" -> resta.value = number1.toDouble() - number2.toDouble()
                "*" -> multiplicacion.value = number1.toDouble() * number2.toDouble()
                //Condición para que no se pueda dividir entre cero
                "/" -> {
                    if (number2.toDouble() != 0.0) {
                        division.value = number1.toDouble() / number2.toDouble()
                    } else {
                        _errorMsg.value = "No se puede dividir por cero"
                    }
                }
                else -> _errorMsg.value = "Operación no válida"
            }
        }
    }
}
