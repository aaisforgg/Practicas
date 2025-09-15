package com.example.practicas

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicas.ui.theme.PracticasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticasTheme {
                 Practica3();
                }
            }
        }
    }


@Composable
fun Practica3(){

    var sueldo by remember { mutableStateOf("") }
    val context = LocalContext.current //para usar el textfield y boton
    var cantidadisr by remember { mutableStateOf("") }
    var sueldototal by remember { mutableStateOf("") }


    Column (modifier = Modifier.fillMaxSize().padding(10.dp,10.dp,10.dp,30.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Row() {
            Image(painter = painterResource(id = R.drawable.sat), contentDescription = null)
        }
        Row (modifier = Modifier.padding(20.dp)){
            OutlinedTextField(value=sueldo,label={Text("Ingresa el sueldo")}, onValueChange = {sueldo=it})
        }
        Row() {
            Button(onClick = {
                if (sueldo.isNotEmpty()){
                    val ingreso = sueldo.toFloat()
                    val isr = calcularISR(ingreso)
                    val total = ingreso - isr
                    cantidadisr = String.format("%.2f", isr)
                    sueldototal = total.toString()
                    }
            }, shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue)) {
                Text("Calcular ISR", color = Color.White)
            }
        }
        Row (modifier = Modifier.padding(10.dp)){
            OutlinedTextField(value=cantidadisr,label={Text("Cantidad de ISR")}, onValueChange = {cantidadisr=it},
                readOnly = true)
        }
        Row (modifier = Modifier.padding(10.dp)){
            OutlinedTextField(value= sueldototal,label={Text("Sueldo real estimado")}, onValueChange = {sueldototal=it }, readOnly = true)
        }

    }

}

// Definición de un rango ISR
data class RangoISR(
    val inferior: Float,
    val superior: Float?,
    val cuotaFija: Float,
    val porcentaje: Float
)
// Tabla ISR quincenal 2025
val tablaISRQuincenal2025 = listOf(
    RangoISR(0.01f, 368.10f, 0.00f, 1.92f),
    RangoISR(368.11f, 3124.35f, 7.05f, 6.40f),
    RangoISR(3124.36f, 5490.75f, 183.45f, 10.88f),
    RangoISR(5490.76f, 6382.80f, 441.00f, 16.00f),
    RangoISR(6382.81f, 7641.90f, 583.65f, 17.92f),
    RangoISR(7641.91f, 15412.80f, 809.25f, 21.36f),
    RangoISR(15412.81f, 24292.65f, 2469.15f, 23.52f),
    RangoISR(24292.66f, 46378.50f, 4557.75f, 30.00f),
    RangoISR(46378.51f, 61838.10f, 11183.40f, 32.00f),
    RangoISR(61838.11f, 185514.30f, 16130.55f, 34.00f),
    RangoISR(185514.31f, null, 58180.35f, 35.00f) // null = en adelante
)

fun calcularISR(ingreso: Float): Float {
    for (r in tablaISRQuincenal2025) {
        if ((ingreso >= r.inferior) && (r.superior == null || ingreso <= r.superior)) {
            val excedente = ingreso - r.inferior
            return r.cuotaFija + excedente * (r.porcentaje / 100f)
        }
    }
    return 0f
}

