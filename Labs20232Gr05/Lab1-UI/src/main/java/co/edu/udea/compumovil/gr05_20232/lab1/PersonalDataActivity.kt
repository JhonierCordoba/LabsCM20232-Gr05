package co.edu.udea.compumovil.gr05_20232.lab1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.res.Configuration
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import co.edu.udea.compumovil.gr05_20232.lab1.ui.theme.Labs20232Gr05Theme
import java.util.Calendar
import java.util.Date

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
    val sexOptions = listOf(stringResource(R.string.male), stringResource(R.string.female))
    val sexSelectedOption = ""
    val configuration = LocalConfiguration.current

    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Text("Landscape")
        }
        else -> {
            ConstraintLayout {
                val (form, button) = createRefs()
                Column(modifier = Modifier.constrainAs(form){
                    top.linkTo(parent.top)
                }
                    ) {
                    Title()
                    DividerComponent()
                    NameField(name) { name = it }
                    LastNameField(lastName) { lastName = it }
                    GenderRadioButtonGroup(sexOptions, sexSelectedOption)
                    DateOfBirthComponent()
                    ScholarGradeSpinner()
                }
                Button(onClick = {

                },
                    modifier = Modifier.constrainAs(button){
                      bottom.linkTo(parent.bottom, margin = 5.dp)
                      end.linkTo(parent.end, margin = 5.dp)
                    },
                    colors = ButtonDefaults.buttonColors() ) {
                    Text(text = stringResource(R.string.next), color = Color.White)
                }
            }

        }
    }


}

@Composable
fun Title() {
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
            .padding(start = 10.dp, top = 10.dp)
            .fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.People , contentDescription = null)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.sex)
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

@Composable
fun DateOfBirthComponent() {
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val dateText = stringResource(R.string.date_of_birth)


    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()


    val mDate = remember { mutableStateOf(dateText) }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.Cake , contentDescription = null)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            fontWeight = FontWeight.Bold,
            text = mDate.value
        )
        Spacer(modifier = Modifier.width(10.dp))
        Button(onClick = {
            mDatePickerDialog.show()
        },
            colors = ButtonDefaults.buttonColors() ) {
            Text(text = stringResource(R.string.change), color = Color.White)
        }
    }
}

@Composable
fun ScholarGradeSpinner(){
    var expanded by remember { mutableStateOf(false) }
    val scholarGrade = stringResource(R.string.scholar_grade)
    val items = listOf(stringResource(R.string.elementary), stringResource(R.string.secondary), stringResource(R.string.university), stringResource(R.string.other))
    var selectedItem by remember { mutableStateOf(scholarGrade) }

    Box(
        modifier = Modifier
            .width(
                300
                    .dp
            )
            .padding(16.dp)
    ) {
        Text(
            text = selectedItem,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .border(2.dp, Color.Gray)
                .padding(16.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedItem = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}
@Preview
@Composable
fun ShowPersonalData(){
    PersonalData()
}




