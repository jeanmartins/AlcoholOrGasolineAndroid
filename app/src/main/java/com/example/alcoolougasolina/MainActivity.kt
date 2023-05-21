package com.example.alcoolougasolina

import android.R
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {

    private var percentual: Double = 0.7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shared : SharedPreferences = getSharedPreferences("switch", Context.MODE_PRIVATE);
        var edShared = shared.edit();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        setContentView(R.layout.activity_main)

        val btCalc: Button = findViewById(R.id.btCalcular)
        val mySwitch = findViewById<Switch>(R.id.swPercentual);
        if(shared.getBoolean("switch",false) == true)
            setPercentual(0.75);
        Log.d("PDM23", "No onCreate, $percentual")


        mySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            // Lógica a ser executada quando o estado do CheckBox é alterado
            if (isChecked) {
                edShared.putBoolean("switch",isChecked);
                edShared.commit();
            }
        }

        btCalc.setOnClickListener(View.OnClickListener {
            //código do evento
            val alcoholEditText = findViewById<EditText>(R.id.edAlcool);
            val isAlcoholEditTextEmpty = checkIfIsEmpty(alcoholEditText);
            val gasEditText = findViewById<EditText>(R.id.edGasolina);
            val isGasEditTextEmpty = checkIfIsEmpty(gasEditText);

            if(!isAlcoholEditTextEmpty || !isGasEditTextEmpty)
                return@OnClickListener;
            if (mySwitch.isChecked)
                setPercentual(0.75);

            val gasValue = getFloatValueFromEditText(gasEditText);
            val alcoholValue = getFloatValueFromEditText(alcoholEditText);
            val alcoholbyGasValue : Double = alcoholValue/gasValue;
            val p = getPercentual();
            if(alcoholbyGasValue <= getPercentual())
                Toast.makeText(this,"Valor do Álcool é rentável",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Valor da Gasolina é rentável",Toast.LENGTH_SHORT).show();
            Log.d("PDM23", "No btCalcular, $percentual");
        })

        }
    private fun checkIfIsEmpty(editText: EditText) : Boolean{
        try{
            if (editText.text.toString().trim().isEmpty()) {
                editText.error = "Este campo é obrigatório";
                return false;
            }
            return true;
        }catch (e : Exception){
            return false;
        }
    }
    private fun getFloatValueFromEditText(editText: EditText) : Double{
        try{
            val editText = editText.text.toString();
            return editText.toDouble();
        }catch (e : Exception){
            Log.d("PDM23", "Ocorreu um erro ao obter valor da variável");
            return 0.0;
        }
    }
    fun setPercentual(novoNumero: Double) {
        percentual = novoNumero
    }

    fun getPercentual() : Double {
        return percentual;
    }
    override fun onResume() {
        super.onResume()
        Log.d("PDM23", "No onResume, $percentual")
    }

    override fun onStart() {
        super.onStart()
        Log.d("PDM23", "No onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("PDM23", "No onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("PDM23", "No onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PDM23", "No onDestroy")
    }
     fun onClickBtCalcular(v: View) {
        //código do evento
        percentual = 0.75
        Log.d("PDM23", "No onClik, $percentual")
    }
}

