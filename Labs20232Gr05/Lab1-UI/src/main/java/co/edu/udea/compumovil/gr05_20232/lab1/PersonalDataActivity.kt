package co.edu.udea.compumovil.gr05_20232.lab1

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr05_20232.lab1.ui.theme.Labs20232Gr05Theme

class PersonalDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20232Gr05Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PersonalData()
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun PersonalData() {
    val sexOptions = listOf("Hombre", "Mujer")
    val sexSelectedOption = ""
    val configuration = LocalConfiguration.current

    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Text("Landscape")
        }
        else -> {
            Column() {
                PersonalTitle()
                DividerComponent()
                NameField(name) { name = it }
                LastNameField(lastName) { lastName = it }
                GenderRadioButtonGroup(sexOptions, sexSelectedOption)
            }
        }
    }


}

@Composable
fun PersonalTitle() {
    Text(
        text = stringResource(R.string.title_activity_personal_data),
        style = MaterialTheme.typography.h5, color = Color.Gray,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(top = 10.dp, start = 20.dp)
    )
}

@Composable
fun DividerComponent() {
    Divider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier
            .padding(vertical = 8.dp)
    )
}

@Composable
fun NameField(
    name: String,
    onNameChange: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp)) {
        Icon(imageVector = Icons.Default.Person, contentDescription = null)
        Spacer(modifier = Modifier.width(10.dp))
        TextFieldComponent(name, onNameChange, stringResource(R.string.name))
    }
}

@Composable
fun LastNameField(
    lastName: String,
    onLastNameChange: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .padding(start = 10.dp, top = 20.dp)){
        Icon(imageVector = Icons.Default.Person , contentDescription = null)
        Spacer(modifier = Modifier.width(10.dp))
        TextFieldComponent(lastName, onLastNameChange, stringResource(R.string.last_name))
    }
}

@Composable
fun TextFieldComponent(
    text: String,
    onTextChange: (String) -> Unit,
    labelText: String
) {
    TextField(
        label = { Text(labelText) },
        value = text,
        textStyle = LocalTextStyle.current.copy(fontSize = 5.sp),
        maxLines = 1,
        onValueChange = onTextChange,
        modifier = Modifier
            .width(250.dp)
            .border(1.dp, Color.Gray)
    )
}

@Composable
fun GenderRadioButtonGroup(
    options: List<String>, option: String,
) {
    var selectedOption by remember { mutableStateOf(option) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp)
            .fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.People , contentDescription = null)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            fontWeight = FontWeight.Bold,
            text = "Sexo:"
        )

        options.forEach { text ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = text == selectedOption,
                    onClick = { selectedOption = text }
                )

                Text(
                    text = text,
                    color = Color.Gray,
                    modifier = Modifier.clickable { selectedOption = text })
            }
        }
    }
}




