package com.example.gymmatekotlin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gymmatekotlin.PieChart
import com.example.gymmatekotlin.PieChartEntry
import com.example.gymmatekotlin.ui.theme.Carbs
import com.example.gymmatekotlin.ui.theme.Fat
import com.example.gymmatekotlin.ui.theme.Protein

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaloriesScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
    ) {innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(state = rememberScrollState()),

            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val entries = listOf(
                    PieChartEntry(color = Protein, 0.3f),
                    PieChartEntry(color = Carbs, 0.4f),
                    PieChartEntry(color = Fat, 0.3f)
                )
                PieChart(entries)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(15.dp)
                                .height(15.dp)
                                .background(color = Protein)
                        )
                        Text(
                            text = "Protein",
                            modifier = Modifier

                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(15.dp)
                                .height(15.dp)
                                .background(color = Carbs)
                        )
                        Text(
                            text = "Carbs",
                            modifier = Modifier
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(15.dp)
                                .height(15.dp)
                                .background(color = Fat)
                        )
                        Text(
                            text = "Fat",
                            modifier = Modifier
                        )
                    }
                    Text(
                        text = "Current weight",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    )
                    Text(
                        text = "0 kg",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            Text(
                text = "Calories required",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
            Text(
                text = "O cal",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "Nutrition required",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
            Text(
                text = "O g Protein",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "O g Carbs",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "O g Fat",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "Food consumption",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
            
        }
    }
}
