package com.example.jokeapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun JokeHome() {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "joke",
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                fontSize = 33.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

        }

    }
}