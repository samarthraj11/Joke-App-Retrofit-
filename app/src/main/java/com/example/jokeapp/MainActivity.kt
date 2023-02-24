package com.example.jokeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.jokeapp.ui.theme.JokeAppTheme
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var joke = "nothing"
                    lifecycleScope.launchWhenCreated {
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
                            joke = response.body()!!.setup
                        }
                        Log.d("output", joke)
                    }
                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = joke)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
