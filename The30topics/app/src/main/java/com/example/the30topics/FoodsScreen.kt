package com.example.the30topics

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.the30topics.data.FoodRepository
import com.example.the30topics.model.Food
import com.example.the30topics.ui.theme.The30TopicsTheme

@Composable
fun ListFood(
    foods: List<Food>,
    padding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn (contentPadding = padding) {
        itemsIndexed(foods) {index, item ->
            FoodItem(
                index + 1,
                item,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }

}

@Composable
fun FoodItem(
    day: Int,
    food: Food,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            contentColor = MaterialTheme.colorScheme.scrim,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.tertiary
            ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_medium)
                )
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.bandera_mexico),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(MaterialTheme.shapes.large)
                )
                Text(
                    text = stringResource(R.string.day, day.toString()),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_small)
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    expanded = !expanded
                }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = stringResource(R.string.expandir),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
            Text(
                text = stringResource(food.tituloRes),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
            )
            Image(
                painter = painterResource(food.imagenRes),
                contentDescription = stringResource(food.tituloRes),
                //Crop rellena la imagen a las medidas del parent
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .fillMaxWidth()
                    .heightIn(max = 180.dp)
            )
            if (expanded) {
                Text(
                    text = stringResource(R.string.descripcion_label),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.padding_medium)
                    )
                )
                Text(
                    text = stringResource(food.descripcionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun TipPreview() {
    val day = 1
    The30TopicsTheme {
        FoodItem(
            day,
            Food(
                tituloRes = R.string.titulo_1,
                imagenRes = R.drawable.image_1,
                descripcionRes = R.string.descripcion_1
            ),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Preview
@Composable
fun ListPreview() {
    The30TopicsTheme {
        ListFood(foods = FoodRepository.comidas)
    }
}