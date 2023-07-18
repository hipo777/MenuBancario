package org.demre.menubancario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import org.demre.menubancario.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var saldo = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOk.setOnClickListener {
            handleOkButtonClick()
        }
    }

    private fun handleOkButtonClick() {
        val selectedOptionId = binding.rGBanco.checkedRadioButtonId
        val selectedOption = findViewById<RadioButton>(selectedOptionId)

        when (selectedOption) {
            binding.rBsaldo -> {
                verSaldo()
            }
            binding.rBingresar -> {

                ingresarDinero()
            }
            binding.rBretirar -> {
                sacarDinero()
            }
        }
    }

    private fun verSaldo() {
        binding.eTmonto.setText(saldo.toString())

    }

    private fun ingresarDinero() {

        val monto = binding.eTmonto.text.toString().toDoubleOrNull()
        if (monto != null && monto > 0) {
            saldo += monto
            limpiarMonto()
            val mensaje = "Se ingresaron $monto. Saldo actual: $saldo"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Ingresa un monto válido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sacarDinero() {
        val monto = binding.eTmonto.text.toString().toDoubleOrNull()
        if (monto != null && monto > 0) {
            if (monto > saldo) {
                Toast.makeText(this, "No tienes suficiente saldo para realizar esta operación.", Toast.LENGTH_SHORT).show()
            } else {
                saldo -= monto
                limpiarMonto()
                val mensaje = "Se retiraron $monto. Saldo actual: $saldo"
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Ingresa un monto válido", Toast.LENGTH_SHORT).show()
        }
    }
    private fun limpiarMonto(){
        binding.eTmonto.text.clear()
    }
}