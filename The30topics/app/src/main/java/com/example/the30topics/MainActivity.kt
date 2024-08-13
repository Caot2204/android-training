package com.example.the30topics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.the30topics.data.FoodRepository
import com.example.the30topics.ui.theme.The30TopicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            The30TopicsTheme {
                TipsApp(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.error)
                )
            }
        }
    }
}

@Composable
fun TipsApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            FoodTopBar()
        },
        modifier = modifier
    ) {
        ListFood(
            FoodRepository.comidas,
            it
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.icono),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(MaterialTheme.shapes.large)
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    )
}

@Preview
@Composable
fun AppPreview() {
    The30TopicsTheme {
        TipsApp()
    }
}
