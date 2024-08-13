package com.example.tarjetadepresentacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarjetadepresentacion.ui.theme.TarjetaDePresentacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TarjetaDePresentacionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TarjetaPresentacion()
                }
            }
        }
    }
}

@Composable
fun TarjetaPresentacion() {
    Column (
        Modifier
            .fillMaxWidth()
            .background(Color(0xFFcae3cb))
    ) {
        ComposableCargo()
        ComposableContacto()
    }
}

@Composable
fun ComposableCargo(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.android_logo)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier.background(Color(0xFF000063))
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = modifier
                    .width(120.dp)
                    .height(120.dp)
            )
        }
        Text(
            text = stringResource(R.string.nombre),
            fontSize = 26.sp
        )
        Text(
            text = stringResource(R.string.cargo),
            color = Color(0xFF228b09),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ComposableContacto (modifier: Modifier = Modifier) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column {
            Icon(
                Icons.Rounded.Call, contentDescription = null,
                modifier = modifier.padding(bottom = 16.dp),
                tint = Color(0xFF228b09)
            )
            Icon(
                Icons.Rounded.Share, contentDescription = null,
                modifier = modifier.padding(bottom = 16.dp),
                tint = Color(0xFF228b09)
            )
            Icon(
                Icons.Rounded.MailOutline, contentDescription = null,
                modifier = modifier.padding(bottom = 16.dp),
                tint = Color(0xFF228b09)
            )
        }
        Column (
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.telefono),
                modifier = modifier.padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(R.string.compartir),
                modifier = modifier.padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(R.string.email),
                modifier = modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    TarjetaDePresentacionTheme {
        TarjetaPresentacion()
    }
}