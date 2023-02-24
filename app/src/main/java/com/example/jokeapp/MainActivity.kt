package com.example.jokeapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jokeapp.ui.theme.JokeAppTheme
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val joke = remember {
                        mutableStateOf("")
                    }
                    LaunchedEffect(key1 = Unit, block = launchWhenCreated@{
                        val response = try {
                            RetrofitInstance.api.getJoke()
                        } catch (
                            e: IOException
                        ) {
                            Log.e("something", "No internet")
                            return@launchWhenCreated
                        } catch (e: HttpException) {
                            Log.e("something", "request error")
                            return@launchWhenCreated
                        }
                        if (response.isSuccessful && response.body() != null) {
                            joke.value = response.body()!!.setup
                            Log.d("output", joke.value)

                        }
                    })
                    HomeScreen(joke = joke.value)
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreen(joke: String = "Sample") {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Nothing")
        Text(text = joke)
    }
}


