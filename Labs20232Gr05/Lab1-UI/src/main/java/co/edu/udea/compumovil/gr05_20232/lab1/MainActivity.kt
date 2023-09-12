package co.edu.udea.compumovil.gr05_20232.lab1

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import co.edu.udea.compumovil.gr05_20232.lab1.ui.theme.Labs20232Gr05Theme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20232Gr05Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EditTextExample()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditTextExample() {
    // ViewModel to hold the text state
    val viewModel: EditTextViewModel = viewModel()
    var texto: String

    Column {
        TextField(
            label = { Text("Text") },
            value = viewModel.textState.value,
            onValueChange = {
                viewModel.textState.value = it
                texto = it.toString()
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .padding(16.dp)
        )

        Text(
            text = "You typed: ${viewModel.textState.value}",
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

// ViewModel to hold the text state
class EditTextViewModel : ViewModel() {
    val textState = mutableStateOf("")
}