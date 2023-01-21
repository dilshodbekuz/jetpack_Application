package com.example.jetpackapplication.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapplication.R
import com.example.jetpackapplication.ui.theme.LightRed
import com.example.jetpackapplication.ui.theme.Purple200
import com.example.jetpackapplication.ui.theme.Purple500
import com.example.jetpackapplication.ui.theme.Purple700

@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .background(Color.Cyan)
        .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(features = listOf(
                Feature(
                    title = "Sleep meditation",
                    R.drawable.ic_baseline_search_24,
                    Purple200,
                    Color.White,
                    Color.Green
                ),
                Feature(
                    title = "Sleep meditation",
                    R.drawable.image,
                    Purple200,
                    Color.Black,
                    Color.Yellow
                ),
                Feature(
                    title = "Sleep meditation",
                    R.drawable.image,
                    Color.White,
                    Color.Red,
                    Purple200
                ),
                Feature(
                    title = "Sleep meditation",
                    R.drawable.image,
                    Color.Red,
                    Color.Green,
                    Color.White
                ),
                Feature(
                    title = "Sleep meditation",
                    R.drawable.image,
                    Purple200,
                    Purple500,
                    Purple700
                ),
                Feature(
                    title = "Sleep meditation",
                    R.drawable.image,
                    Purple200,
                    Purple500,
                    Purple700
                ),
            ))
        }
        BottomMenu(items = listOf(
            BottomMenuContent("Search",R.drawable.ic_baseline_search_24),
            BottomMenuContent("Search",R.drawable.ic_baseline_search_24),
            BottomMenuContent("Search",R.drawable.ic_baseline_search_24),
            BottomMenuContent("Search",R.drawable.ic_baseline_search_24),
            BottomMenuContent("Search",R.drawable.ic_baseline_search_24),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = Color.Green,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Black,
    initialSelectedItemIndex: Int = 0,
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Color.Blue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Purple500,
    onItemClick: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(painterResource(id = R.drawable.image), contentDescription = item.title,
                tint = if (isSelected) activeHighlightColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(text = item.title,
            color = if (isSelected) activeHighlightColor else inactiveTextColor)
    }

}

@Composable
fun GreetingSection(
    name: String = "Dilshoobek",
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good morning $name",
                style = MaterialTheme.typography.h5
            )

            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.body1
            )
        }
        Icon(painter = painterResource(id = com.example.jetpackapplication.R.drawable.ic_baseline_search_24),
            contentDescription = "Search",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )

    }
}

@Composable
fun ChipSection(
    chips: List<String>,
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) Color.Blue
                        else Color.DarkGray
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = Color.White)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed,
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.h4
            )

            Text(
                text = "Meditation  3-10 min",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Blue)
                .padding(10.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.image), contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Feature",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(15.dp)
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 6.5.dp),
            modifier = Modifier.fillMaxHeight()
        )
        {
            items(features.size)
            {
                FeatureItem(feature = features[it])
            }
        }

    }
}

@Composable
fun FeatureItem(
    feature: Feature,
) {
    BoxWithConstraints(modifier = Modifier
        .padding(7.5.dp, bottom = 70.dp)
        .aspectRatio(1f)
        .clip(RoundedCornerShape(10.dp))
        .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //medium Color path
        val mediumColorPoint1 = Offset(0f, height * 0.3f)
        val mediumColorPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColorPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColorPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColorPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColorPath = Path().apply {
            moveTo(mediumColorPoint1.x, mediumColorPoint1.y)
            standardQuadFromTo(mediumColorPoint1, mediumColorPoint2)
            standardQuadFromTo(mediumColorPoint2, mediumColorPoint3)
            standardQuadFromTo(mediumColorPoint3, mediumColorPoint4)
            standardQuadFromTo(mediumColorPoint4, mediumColorPoint5)
            PathNode.LineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            PathNode.LineTo(-100f, height.toFloat() + 100f)
            close()
        }
        //lightColorPath

        val lightColorPath1 = Offset(0f, height * 0.3f)
        val lightColorPath2 = Offset(width * 0.1f, height * 0.4f)
        val lightColorPath3 = Offset(width * 0.3f, height * 0.35f)
        val lightColorPath4 = Offset(width * 0.65f, height.toFloat())
        val lightColorPath5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightColorPath1.x, lightColorPath1.y)
            standardQuadFromTo(lightColorPath1, lightColorPath2)
            standardQuadFromTo(lightColorPath2, lightColorPath3)
            standardQuadFromTo(lightColorPath3, lightColorPath4)
            standardQuadFromTo(lightColorPath4, lightColorPath5)
            PathNode.LineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            PathNode.LineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            drawPath(
                path = mediumColorPath,
                color = feature.mediumColor)
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
        ) {
            Text(text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(painterResource(id = R.drawable.image), contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {

                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Blue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}