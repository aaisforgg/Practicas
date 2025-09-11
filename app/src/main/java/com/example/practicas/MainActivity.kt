package com.example.practicas

import android.R
import android.R.attr.fontFamily
import android.graphics.fonts.FontFamily
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Practica1();
                }
            }
        }
    }
}

@Composable

fun Practica1(){
    var Resultado by remember { mutableStateOf("") }
    val context = LocalContext.current //para usar el textfield y boton
    var valorA by remember { mutableStateOf("") }
    var valorB by remember { mutableStateOf("") }



    Column(modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier.padding(2.dp, 300.dp, 2.dp, 2.dp))
        {
            TextField(
                value = valorA, onValueChange = { valorA = it },
                placeholder = { Text(text = "Valor A") }
            )
        }

        //otro textfield
        Row(modifier = Modifier.padding(2.dp, 20.dp, 2.dp, 2.dp))
        {
            TextField(
                value = valorB, onValueChange = { valorB = it },
                placeholder = { Text(text = "Valor B") }
            )
        }

        Row(modifier = Modifier.padding(2.dp, 20.dp, 2.dp, 2.dp)) {
            Button(
                onClick = {
                    val a = valorA.toInt()
                    val b = valorB.toInt()
                    val c = a + b
                    Resultado = c.toString()
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 25.dp,
                    disabledElevation = 0.dp,

                    )
            ) {
                Text("Enviar")
            }

            Button(
                onClick = {
                    valorA = ""
                    valorB = ""
                    Resultado = ""
                },
                colors = ButtonDefaults.buttonColors(Color.Blue),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 25.dp,
                    disabledElevation = 0.dp,

                    )
            ) {
                Text("Borrar")
            }
        }

        Row(modifier = Modifier.padding(2.dp, 20.dp, 2.dp, 2.dp))
        {
            TextField(
                value = Resultado, onValueChange = { Resultado = it },
                placeholder = { Text(text = "Resultado") }
            )
        }
    }
    }

