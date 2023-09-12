package co.edu.udea.compumovil.gr05_20232.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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
                    EditTextExample(getString(R.string.hello))
                }
            }
        }
    }
}

@Composable
fun EditTextExample(texto: String) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val viewModel: EditTextViewModel = viewModel()

        val (nameTextField, nameText, boxBlue) = createRefs()

        TextField(
            label = { Text(texto) },
            value = viewModel.textState.value,
            onValueChange = {
                viewModel.textState.value = it
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .padding(16.dp)
                .constrainAs(nameTextField) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(modifier = Modifier.constrainAs(nameText) {
            top.linkTo(parent.bottom)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, text = "Text")

        Text(modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, text = "nameText")
    }
}

// ViewModel to hold the text state
class EditTextViewModel : ViewModel() {
    val textState = mutableStateOf("")
}
