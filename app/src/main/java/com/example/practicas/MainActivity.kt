package com.example.practicas

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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

    Column () {
        Row() {
            Image(painter = painterResource(id = R.drawable.sat), contentDescription = null)
        }
        Row (modifier = Modifier.padding(20.dp)){
            OutlinedTextField(value=sueldo,label={Text("Ingresa el sueldo")}, onValueChange = {sueldo=it})
        }
        Row() {
            Button(onClick = {}, shape = RoundedCornerShape(40.dp)) {
                Text("Botón redondeado")
            }
        }

    }

}
