package com.example.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

var totalExprimir = 0
var vecesExprimido = 0

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                imagenConTexto()
            }
        }
    }
}


@Preview (showSystemUi = true)
@Composable
fun LemonApp() {
    imagenConTexto(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun imagenConTexto(modifier: Modifier = Modifier) {

    var stateGlass by remember {mutableStateOf(1)}

    val imageResource = when(stateGlass) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val stringLemon = when(stateGlass) {
        1 -> stringResource(R.string.tap_lemon)
        2 -> stringResource(R.string.lemon_squeeze)
        3 -> stringResource(R.string.drink)
        else -> stringResource(R.string.start_again)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Image(
            painter = painterResource(imageResource),
            contentDescription = stringLemon,
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(colorResource(id = R.color.background_lemon))
                .size(200.dp)
                .clickable {
                    if (stateGlass == 2 && vecesExprimido < totalExprimir) {
                        vecesExprimido++
                        if (vecesExprimido == totalExprimir) {
                            stateGlass++
                            vecesExprimido = 0
                            totalExprimir = 0
                        }
                    } else {
                        stateGlass++
                        if (stateGlass > 4) stateGlass = 1
                        if (stateGlass == 2 && totalExprimir == 0) totalExprimir = (2..4).random()
                    }
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringLemon,
            fontSize = 18.sp)
    }
}

