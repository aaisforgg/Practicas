package com.example.practicas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraUI()
        }
    }
}

@Composable
fun CalculadoraUI() {
    var expresion by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("0") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Pantalla
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFDDDDDD))
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Text(expresion, fontSize = 22.sp, textAlign = TextAlign.End)
            Text(resultado, fontSize = 36.sp, fontWeight = FontWeight.Bold)
        }

        // Botones
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Boton("Borrar", Color.Red, Modifier.weight(2f)) {
                    expresion = ""
                    resultado = "0"
                }
                Boton("⌫", Color.Blue, Modifier.weight(1f)) {
                    if (expresion.isNotEmpty()) expresion = expresion.dropLast(1)
                }
                Boton("/", Color.LightGray, Modifier.weight(1f)) { expresion += "/" }
            }

            val filas = listOf(
                listOf("1", "2", "3", "x"),
                listOf("4", "5", "6", "-"),
                listOf("7", "8", "9", "+"),
                listOf(".", "0", "=", "")
            )

            filas.forEach { fila ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    fila.forEach { simbolo ->
                        if (simbolo.isNotEmpty()) {
                            Boton(
                                texto = simbolo,
                                colorFondo = when (simbolo) {
                                    "=", "⌫" -> Color.Blue
                                    "Borrar" -> Color.Red
                                    "/", "x", "-", "+" -> Color.LightGray
                                    else -> Color.Black
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                when (simbolo) {
                                    "=" -> {
                                        try {
                                            val expFinal = expresion.replace("x", "*")
                                            resultado = evalBasico(expFinal).toString()
                                        } catch (e: Exception) {
                                            resultado = "Error"
                                        }
                                    }
                                    else -> expresion += simbolo
                                }
                            }
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Boton(
    texto: String,
    colorFondo: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = colorFondo),
        shape = CircleShape,
        modifier = modifier.height(70.dp)
    ) {
        Text(texto, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

fun evalBasico(expr: String): Double {
    val partes = Regex("([+\\-*/])").split(expr).filter { it.isNotBlank() }.toMutableList()
    val ops = Regex("([0-9.]+)").split(expr).filter { it.isNotBlank() }

    var resultado = partes[0].toDouble()
    var i = 1
    ops.forEach { op ->
        val num = partes[i].toDouble()
        when (op) {
            "+" -> resultado += num
            "-" -> resultado -= num
            "*" -> resultado *= num
            "/" -> resultado /= num
        }
        i++
    }
    return resultado
}

