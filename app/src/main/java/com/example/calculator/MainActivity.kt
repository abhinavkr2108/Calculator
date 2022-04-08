package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    var lastNumber = false
    var lastDecimal = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumber = true
        lastDecimal = false
    }

    fun clrButton(view: View) {
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View) {
        if (lastNumber ==true && lastDecimal==false){
            tvInput?.append(".")
            lastNumber = false
            lastDecimal = true
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastNumber && !isOperatorAdded(it.toString())){
                tvInput?.append(((view as Button).text))
                lastNumber = false
                lastDecimal = false
            }
        }
    }

    fun onEqual(view: View){
        if (lastNumber==true) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                //Subtraction Operation
                if (tvValue.startsWith("-")){
                    prefix= "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    //result
                    tvInput?.text =removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                //Addition Operation
                else if (tvValue.contains("+")){
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    //result
                    tvInput?.text =removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }

                //Multiplication Operation
                else if (tvValue.contains("×")){
                    var splitValue = tvValue.split("×")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    //result
                    tvInput?.text =removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

                // Division Operation
                else if (tvValue.contains("/")){
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    //result
                    tvInput?.text =removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }






            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0")){
            value = result.substring(0, result.length-2)

        }
        return value
    }
    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")){
            false
        }
        else{
            value.contains("/") ||
                    value.contains("×") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }
}