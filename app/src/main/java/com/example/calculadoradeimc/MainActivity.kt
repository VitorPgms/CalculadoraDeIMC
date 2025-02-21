package com.example.calculadoradeimc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadoradeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btCalculate = binding.btnCalcular
        val message = binding.txtMensagem

        btCalculate.setOnClickListener {
            val editWeight = binding.editPeso.text.toString()
            val editHeight = binding.editAltura.text.toString()

            if (editWeight.isEmpty()){
                message.setText("Informe o seu Peso")
            }else if (editHeight.isEmpty()){
                message.setText("Informe a sua Altura")
            }else {
                bmiCalculation()
            }
        }

    }

    private fun bmiCalculation(){
        val weightId = binding.editPeso
        val heightId = binding.editAltura

        val weight = Integer.parseInt(weightId.text.toString())
        val height = java.lang.Float.parseFloat(heightId.text.toString())
        val resultado = binding.txtMensagem
        val imc = weight / (height * height)

        val bmiLow = 18.5
        val bmiNormal = 24.9
        val bmiOverweight = 29.9
        val bmiObesity1 = 34.9
        val bmiObesity2 = 39.9

        val messages = when{
            imc <= bmiLow -> getString(R.string.bmi_low)
            imc <= bmiNormal -> getString(R.string.bmi_normal)
            imc <= bmiOverweight -> getString(R.string.bmi_overweight)
            imc <= bmiObesity1 -> getString(R.string.bmi_obesety1)
            imc <= bmiObesity2 -> getString(R.string.bmi_obesety2)
            else -> getString(R.string.bmi_obesety3)
        }

        imc.toString()
        resultado.setText("IMC: $imc \n $messages")


    }

}