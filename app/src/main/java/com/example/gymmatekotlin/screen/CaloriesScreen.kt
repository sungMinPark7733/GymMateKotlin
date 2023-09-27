package com.example.gymmatekotlin.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymmatekotlin.BasicPieChart
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaloriesScreen(
    //viewModel: CaloriesPageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var openWeightBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    var edgeToEdgeEnabled by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        PieCard()
        LatestCard()
        QuickAddCard()
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Button(onClick = {
                openBottomSheet = !openBottomSheet
            }) {
                Text(text = "Add Food")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                openWeightBottomSheet = !openWeightBottomSheet
            }) {
                Text(text = "Add Weight")
            }

        }
    }
    if (openBottomSheet) {
        val windowInsets = if (edgeToEdgeEnabled)
            WindowInsets(0) else BottomSheetDefaults.windowInsets

        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
            windowInsets = windowInsets
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                    // you must additionally handle intended state cleanup, if any.
                    onClick = {
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                openBottomSheet = false
                            }
                        }
                    }
                ) {
                    Text("Hide Bottom Sheet")
                }
            }
            var text by remember { mutableStateOf("") }
            OutlinedTextField(value = text, onValueChange = { text = it })
            LazyColumn {
                items(50) {
                    ListItem(
                        headlineContent = { Text("Item $it") },
                        leadingContent = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        }
    }
    if (openWeightBottomSheet) {
        val windowInsets = if (edgeToEdgeEnabled)
            WindowInsets(0) else BottomSheetDefaults.windowInsets

        ModalBottomSheet(
            onDismissRequest = { openWeightBottomSheet = false },
            sheetState = bottomSheetState,
            windowInsets = windowInsets
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), horizontalArrangement = Arrangement.Center
            ) {
                SliderAdvancedExample()
            }
        }
    }
}

@Composable
fun PieCard(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
        ) {
            BasicPieChart()
            PieCardText()
        }

    }
}

///////////////////////
// Start of Pie Card //
///////////////////////

@Composable
fun PieCardText(
    //user: User
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        // Header
        DisplayText(displayText = "Calories Needed")
        DisplayText(displayText = "2200cal")
        DisplayText(displayText = "Macros")
        DisplayText(displayText = "Protein")
        DisplayText(displayText = "Carbs")
        DisplayText(displayText = "Fat")
    }
}

/////////////////////
// End of Pie card //
/////////////////////

//////////////////////
// Latest Food Card //
//////////////////////

@Composable
fun LatestCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Title
            Text(
                text = "Latest",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()

            )
            var foodList = listOf("burger", "big burger", "cheese burger")
            DisplayLatestFood(foodList = foodList)
        }
    }
}

@Composable
fun DisplayLatestFood(foodList: List<String>, modifier: Modifier = Modifier) {
    var count = 0
    for (food in foodList) {
        if (count > 4) {
            break
        }
        Row() {
            food?.let {
                DisplayText(
                    displayText = " • " + it,
                    modifier.padding(3.dp)
                )
                count++
            }
        }
    }
}

/////////////////////////////
// End of Latest food card //
/////////////////////////////

/////////////////////////////
// Start of Quick add Card //
/////////////////////////////
@Composable
fun QuickAddCard(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        // Title
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Quick Add",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            var foodList = listOf("burger", "big burger", "cheese burger")
            var count = 0
            for (food in foodList) {
                if (count > 2) break
                food?.let {
                    QuickAddRow(food = it)
                }
            }
        }
    }
}

@Composable
fun QuickAddRow(food: String, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = food,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(5.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(5.dp)
        ) {
            Text(text = "Add")
        }
    }
}

///////////////////////////
// End of Quick add card //
///////////////////////////

@Composable
fun SliderAdvancedExample() {
    var sliderPosition by remember { mutableStateOf(0) } // Change mutableIntStateOf to mutableStateOf
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
        modifier = Modifier.fillMaxWidth() // Fill the maximum width
    ) {
        Text(
            text = "Your weight is"
        )
        Slider(
            value = sliderPosition.toFloat(), // Cast sliderPosition to Float for Slider
            onValueChange = {
                sliderPosition = it.toInt()
            }, // Cast it to Int when updating sliderPosition
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            valueRange = 0f..100f
        )
        Text(
            text = "$sliderPosition kg",
            textAlign = TextAlign.Center // Center the text horizontally
        )
        Row(
            horizontalArrangement = Arrangement.Center, // Center-align buttons horizontally
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {}) {
                Text(text = "Cancel")
            }
            Spacer(modifier = Modifier.width(16.dp)) // Add space between buttons
            Button(onClick = {}) {
                Text(text = "Confirm")
            }
        }
    }
}



@Composable
fun DisplayText(displayText: String, modifier: Modifier = Modifier) {
    Text(
        text = displayText,
        modifier = modifier
    )
}

@Preview
@Composable
fun CaloriesPreview() {
    CaloriesScreen()
}