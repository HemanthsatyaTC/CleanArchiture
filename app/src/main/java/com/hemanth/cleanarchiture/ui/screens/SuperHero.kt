package com.hemanth.cleanarchiture.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.hemanth.cleanarchiture.domain.logout
import com.hemanth.cleanarchiture.viewmodel.SuperHeroAdapter
import com.hemanth.data.model.shopping.ShoppingDataItemModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SuperHero(
    category: String,
    viewModel: SuperHeroAdapter = hiltViewModel(),
    navController: NavHostController)
{
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = category) },
                backgroundColor = Color.Cyan,
                contentColor = Color.Black,
                actions = {
                    // Add a Logout button
                    IconButton(onClick = {
                        logout(navController = navController, context)
                    }) {
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Logout", tint = Color.Black)
                    }
                }
            )
        },
        content = { paddingValues ->

            val upcomingData = viewModel.getProductsByCategory(category)
            Column(modifier = Modifier.padding(paddingValues)){
                Spacer(modifier = Modifier.height(5.dp))
                if (upcomingData.isEmpty()) {

                    androidx.compose.material3.Text(text = "Loading...")
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(upcomingData) { data ->
                            SuperHeroData(data = data, navController)

                        }
                    }
                }
            }

        })


}

@Composable
fun SuperHeroData(data: ShoppingDataItemModel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate(
                    "details/${data.id}"
                )
            },
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
