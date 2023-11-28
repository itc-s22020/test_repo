package com.example.imgrgblsb

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
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
import androidx.compose.ui.platform.LocalContext
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

fun zipRead(context: Context) {
    val zipFile = context.assets.open("zip/test.zip")
    val zipData = zipFile.readBytes()
    val zipSize = zipData.size
    println("%x".format(zipData[0].toInt()))
    for (i in 0 until zipSize) {
        val target = zipData[i].toInt()
        println(target and 0x01)
    }
//    var target = (zipData[3].toInt()) + (zipData[4].toInt() shl 8) + (zipData[5].toInt() shl 16)
//    println("R:${target and 0x01}")
//    target = target ushr 1
//    println("G:${target and 0x01}")
//    target = target ushr 1
//    println("B:${target and 0x01}")

}
fun Bitmap.test(context: Context): Bitmap {
    val pixels = IntArray(width * height)
    println(pixels.contentToString())
    getPixels(pixels, 0, width, 0, 0, width, height)
    println(pixels.contentToString())

    zipRead(context)

    for (y in 0 until height) {
        for (x in 0 until width) {
            val pixel = pixels[x + y * width]

            //ARGB
            val pixelAlpha: Int = (pixels[x + y * width] shr 24) and 0xff
            val pixelRed   : Int = (pixel shr 16) and 0xff
            val pixelGreen : Int = (pixel shr 8 ) and 0xff
            val pixelBlue  : Int =  pixel         and 0xff

            //set
            pixels[x + y * width] =  Color.argb(255, pixelRed , pixelGreen , pixelBlue )
        }
    }

    return copy(Bitmap.Config.ARGB_8888, true).apply {
        setPixels(pixels, 0, width, 0, 0, width, height)
    }
}

@Composable
fun test(): Bitmap {
    val hoge = ImageBitmap.imageResource(id = R.drawable.test)
    return hoge.asAndroidBitmap().test(LocalContext.current)
}


