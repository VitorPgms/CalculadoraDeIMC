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
        val bt_calcular = binding.btnCalcular
        val mensagem = binding.txtMensagem

        bt_calcular.setOnClickListener {
            val editPeso = binding.editPeso.text.toString()
            val editAltura = binding.editAltura.text.toString()

            if (editPeso.isEmpty()){
                mensagem.setText("Informe o seu Peso")
            }else if (editAltura.isEmpty()){
                mensagem.setText("Informe a sua Altura")
            }else {
                CalculoDeImc()
            }
        }

    }

    private fun CalculoDeImc(){
        val pesoId = binding.editPeso
        val alturaId = binding.editAltura

        val peso = Integer.parseInt(pesoId.text.toString())
        val altura = java.lang.Float.parseFloat(alturaId.text.toString())
        val resultado = binding.txtMensagem
        val imc = peso / (altura * altura)

        val imcBaixo = 18.5
        val imcNormal = 24.9
        val imcSobrepeso = 29.9
        val imcObesidade1 = 34.9
        val imcObesidade2 = 39.9

        val Mensagem = when{
            imc <= imcBaixo -> "Peso Baixo"
            imc <= imcNormal -> "Peso Normal"
            imc <= imcSobrepeso -> "Sobrepeso"
            imc <= imcObesidade1 -> "Obesidade (Grau 1)"
            imc <= imcObesidade2 -> "Obesidade (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }

        imc.toString()
        resultado.setText("IMC: $imc \n $Mensagem")


    }

}