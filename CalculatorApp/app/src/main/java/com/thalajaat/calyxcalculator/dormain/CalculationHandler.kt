package com.thalajaat.calyxcalculator.dormain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat


class CalculationHandler():CalculationHandlerInterface {

    private var expression = MutableStateFlow( "0")

    override fun getExpression():MutableStateFlow<String> = expression

    override fun addValue(value: String){
        expression.value += value
    }

    override fun removeValue() {
        try {
            if (expression.value.length == 1) {
                expression.value = "0"
                return
            }
            expression.update {
                it.dropLast(1)
            }
        }
        catch (e:Exception) {
            expression.value ="0"
        }
    }

    override fun addArithemetic(value: Arithemetics){

        when(value){
            Arithemetics.ADD -> {
                expression.value+="+"
            }
            Arithemetics.SUBTRACT -> {
                expression.value+="-"
            }
            Arithemetics.MODULUS -> {
                expression.value+="#"
            }
            Arithemetics.MULTIPLY -> {
                expression.value+="*"
            }
            Arithemetics.DIVIDE -> {
                expression.value+="/"
            }
        }

    }

    override fun calculate(onError: () -> Unit, onCalculationResponse: (String) -> Unit) {
        try {
            val e = Expression(expression.value)
            val result = e.calculate()
            if (result.isNaN()) {
                onError()
            } else {
                val result = DecimalFormat("0.######").format(result).toString()
                expression.value = result
                // Show Result
                onCalculationResponse(result)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
            onError()
        }
    }
}




interface CalculationHandlerInterface {

    fun addValue(value: String)
    fun removeValue()

    fun addArithemetic(value: Arithemetics)

    fun calculate(onError:()->Unit,onCalculationResponse:(String)->Unit)

    fun getExpression():MutableStateFlow<String>
}



enum class Arithemetics{
    ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULUS
}