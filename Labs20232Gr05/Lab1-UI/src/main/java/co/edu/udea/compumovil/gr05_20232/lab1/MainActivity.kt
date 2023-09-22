package co.edu.udea.compumovil.gr05_20232.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr05_20232.lab1.navigation.AppNavigarion
import co.edu.udea.compumovil.gr05_20232.lab1.ui.theme.Labs20232Gr05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20232Gr05Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigarion()
                }
            }
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    Labs20232Gr05Theme {
        AppNavigarion()
    }
}
