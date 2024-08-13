package com.example.artapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artapp.ui.theme.ArtAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ArtAppLayout()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ArtAppLayout() {

    var state by remember { mutableStateOf(1) }

    val imagenActual = when (state) {
        1 -> R.drawable.imagen1
        2 -> R.drawable.imagen2
        3 -> R.drawable.imagen3
        4 -> R.drawable.imagen4
        else -> R.drawable.imagen5
    }

    val tituloActual = when (state) {
        1 -> R.string.titulo1
        2 -> R.string.titulo2
        3 -> R.string.titulo3
        4 -> R.string.titulo4
        else -> R.string.titulo5
    }

    val autorActual = when (state) {
        1 -> R.string.autor1
        2 -> R.string.autor2
        3 -> R.string.autor3
        4 -> R.string.autor4
        else -> R.string.autor5
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImagenEnPantalla(
            imagenActual
        )
        DescripcionDeImagen(
            titulo = tituloActual,
            autor = autorActual
        )
        Row {
            Button(
                onClick = {
                    if (state > 1) state -= 1
                    else state = 5
                }
            ) {
                Text(stringResource(R.string.button_preview))
            }
            Spacer(modifier = Modifier.padding(40.dp))
            Button(
                onClick = {
                    if (state < 5) state += 1
                    else state = 1
                }
            ) {
                Text(stringResource(R.string.button_next))
            }
        }
    }
}

@Composable
fun ImagenEnPantalla(
    @DrawableRes imagenActual: Int
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        shadowElevation = 16.dp,
        modifier = Modifier
            .padding(
                top = 20.dp,
                bottom = 20.dp
            )
    ) {
        Image(
            painter = painterResource(imagenActual),
            contentDescription = "",
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
                .height(400.dp)
        )
    }
}

@Composable
fun DescripcionDeImagen(
    @StringRes titulo: Int,
    @StringRes autor: Int
) {
    val stringAutor = stringResource(autor)
    
    Surface (
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        shadowElevation = 16.dp,
        modifier = Modifier
            .padding(
                top = 20.dp,
                bottom = 40.dp
            )
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier.padding(20.dp)
        ){
            Text(
                text = stringResource(titulo),
                fontSize = 20.sp
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = stringResource(R.string.autor, stringAutor),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
