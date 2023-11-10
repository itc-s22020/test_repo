package com.example.imgrgblsb

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.imgrgblsb.ui.theme.ImgRGBlsbTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImgRGBlsbTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Image(
        bitmap = test().asImageBitmap(),
        contentDescription = "test"
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImgRGBlsbTheme {
        Greeting("Android")
    }
}


fun Bitmap.test(): Bitmap {
    val pixels = IntArray(width * height)
    println(pixels.contentToString())
    getPixels(pixels, 0, width, 0, 0, width, height)
    println(pixels.contentToString())

    for (y in 0 until height) {
        for (x in 0 until width) {
            //ピクセル操作（この場合は色反転）
            pixels[x + y * width] = pixels[x + y * width] xor 0x00ffffff
        }
    }

    return copy(Bitmap.Config.ARGB_8888, true).apply {
        setPixels(pixels, 0, width, 0, 0, width, height)
    }
}

//fun Bitmap.abcdef(): {

//}



@Composable
fun test(): Bitmap {
    val hoge = ImageBitmap.imageResource(id = R.drawable.test)
    return hoge.asAndroidBitmap().test()
}


