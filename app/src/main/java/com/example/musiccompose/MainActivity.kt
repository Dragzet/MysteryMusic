package com.example.musiccompose

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musiccompose.ui.theme.MusicComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicComposeTheme {
                Main(this)
            }
        }
    }
}


@Composable
fun Main(context: Context){
    val mediaPlayerScary = MediaPlayer.create(context, R.raw.scary)
    val mediaPlayerFun = MediaPlayer.create(context, R.raw.funny)
    val backColor = remember{ mutableStateOf(Color.White)}
    val need = painterResource(id = R.drawable.sun)
    val image = remember { mutableStateOf(need) }
    val n = painterResource(id = R.drawable.moon)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backColor.value),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = image.value,
            contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .clickable {
                    if (backColor.value == Color.White)
                    {
                        mediaPlayerFun.stop()
                        mediaPlayerScary.start()
                        backColor.value = Color.Black
                    }
                    else{
                        mediaPlayerFun.start()
                        mediaPlayerScary.stop()
                        backColor.value = Color.White
                    }

                    if (image.value == need) image.value = n
                    else image.value = need
                },
            contentScale = ContentScale.Fit,
        )
    }
}