package com.mine.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = getColor(R.color.dark_back)

        val etWeight = findViewById<EditText>(R.id.et_Weight)
        val etHeight = findViewById<EditText>(R.id.et_Height)
        val clc_btn = findViewById<Button>(R.id.btn_calculate)

        clc_btn.setOnClickListener{

            val weight = etWeight.text.toString()
            val height = etHeight.text.toString()

            if (validateInputs(weight,height)) {

                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                //get result with two decimals only
                val bmi2Digits = String.format("%.2f", bmi).toFloat()

                displayResult(bmi2Digits)
            }
        }


    }

    fun displayResult(bmi:Float){
        val result_bmi = findViewById<TextView>(R.id.tv_bmi_number)
        val result_description = findViewById<TextView>(R.id.tv_result)

        result_bmi.text = bmi.toString()

        var resultTxt = ""
        var color = 0

        when {
            bmi < 18.5 -> {
                resultTxt = "Under Weight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.99 -> {
                resultTxt = "Healthy Weight"
                color = R.color.normal_weight
            }
            bmi in 25.00..29.99 ->{
                resultTxt = "Over Weight"
                color = R.color.over_weight
            }
            bmi > 29.99 ->{
                resultTxt = "Obese Weight"
                color = R.color.obese_weight
            }
        }

        result_description.text = resultTxt
        result_description.setTextColor(ContextCompat.getColor(this,color))

    }

    fun validateInputs(weight:String?,height:String?):Boolean{

        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is Empty", Toast.LENGTH_SHORT).show()
                return false }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is Empty", Toast.LENGTH_SHORT).show()
                return false }
            else -> {
                return true
            }
        }

    }
}



