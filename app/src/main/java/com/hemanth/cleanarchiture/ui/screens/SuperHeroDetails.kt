package com.hemanth.cleanarchiture.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.hemanth.cleanarchiture.viewmodel.SuperHeroAdapter
import com.hemanth.data.model.shopping.ShoppingDataItemModel

@Composable
fun SuperHeroDetailsScreen(id: Int, viewModel: SuperHeroAdapter = hiltViewModel()) {

    val upcomingData = viewModel.upcomingData.value
    val data = upcomingData.find { it.id == id } ?: return
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Details") },
                backgroundColor = Color.Cyan,
                contentColor = Color.Black
            )
        },
        content = { paddingValues ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .clip(RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.secondaryContainer
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Column for text
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Product Name: ${data.title}",
                            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Left
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Price: ${data.price}$",
                            style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Left
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Category: ${data.rating}",
                            style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Left
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Description: ${data.description}",
                            style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Left
                        )
                    }
                    Image(
                        painter = rememberImagePainter(data.image),
                        contentDescription = data.title,
                        modifier = Modifier
                            .weight(1f)
                            .height(240.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
        }
    )
}
