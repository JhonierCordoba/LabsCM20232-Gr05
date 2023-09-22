package co.edu.udea.compumovil.gr05_20232.lab1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.res.Configuration
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr05_20232.lab1.navigation.AppScreens
import java.util.Calendar
import java.util.Date

@SuppressLint("UnrememberedMutableState")
@Composable
fun PersonalData(navController: NavController) {
    val title = stringResource(R.string.title_activity_personal_data)
    val dateText = stringResource(R.string.date_of_birth)
    val scholarGrade = stringResource(R.string.scholar_grade)

    val scholarSelectedItem = rememberSaveable { mutableStateOf(scholarGrade) }
    val sexOptions = listOf(stringResource(R.string.male), stringResource(R.string.female))
    val selectedOption = rememberSaveable { mutableStateOf("") }
    val mDate = rememberSaveable { mutableStateOf("$dateText*") }
    val configuration = LocalConfiguration.current

    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }



    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            ConstraintLayout {
                val (form) = createRefs()
                Column(
                    modifier = Modifier.constrainAs(form) {
                        top.linkTo(parent.top)
                    },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Title(title)
                    DividerComponent()
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        NameField(name) { name = it }
                        LastNameField(lastName) { lastName = it }
                    }
                    GenderRadioButtonGroup(sexOptions, selectedOption)
                    ScholarGradeSpinner(
                        scholarSelectedItem
                    ) { selected ->
                        scholarSelectedItem.value = selected
                    }
                    DateOfBirthComponent(mDate)
                }
            }
        }

        else -> {
            ConstraintLayout {
                val (form) = createRefs()
                Column(modifier = Modifier.constrainAs(form) {
                    top.linkTo(parent.top)
                }
                ) {
                    Title(title)
                    DividerComponent()
                    NameField(name) { name = it }
                    LastNameField(lastName) { lastName = it }
                    GenderRadioButtonGroup(sexOptions, selectedOption)
                    ScholarGradeSpinner(
                        scholarSelectedItem
                    ) { selected ->
                        scholarSelectedItem.value = selected
                    }
                    DateOfBirthComponent(mDate)

                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Button(
            onClick = {
                if (name.isNotEmpty() && lastName.isNotEmpty() && mDate.value != "$dateText*") {
                    Log.i(
                        "PersonalData",
                        "$title: " +
                                "\n$name $lastName " +
                                if (selectedOption.value.isNotEmpty()) {
                                    "\n${selectedOption.value}"
                                } else {
                                    ""
                                }
                                + "\n${mDate.value} " +
                                if (scholarSelectedItem.value.isNotEmpty()) {
                                    "\n${scholarSelectedItem.value}"
                                } else {
                                    ""
                                }
                    )
                    name = ""
                    lastName = ""
                    selectedOption.value = ""
                    mDate.value = "$dateText*"
                    scholarSelectedItem.value = scholarGrade

                    navController.navigate(route = AppScreens.ContactDataScreen.route)
                }
            },
            Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors()
        ) {
            Text(text = stringResource(R.string.next), color = Color.White)
        }
    }
}

@Composable
fun Title(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.h5, color = Color.Gray,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(top = 10.dp, start = 20.dp)
            .fillMaxWidth()
    )
}

@Composable
fun NameField(
    name: String,
    onNameChange: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp)
    ) {
        IconComponent(Icons.Default.Person, true)
        TextFieldComponent(
            name,
            onNameChange,
            stringResource(R.string.name),
            KeyboardType.Text,
            true
        )
    }
}

@Composable
fun LastNameField(
    lastName: String,
    onLastNameChange: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp)
    ) {
        IconComponent(Icons.Default.Person, true)
        TextFieldComponent(
            lastName,
            onLastNameChange,
            stringResource(R.string.last_name),
            KeyboardType.Text,
            true
        )
    }
}

@Composable
fun GenderRadioButtonGroup(
    options: List<String>, selectedOption: MutableState<String>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
    ) {
        IconComponent(Icons.Default.People)
        Text(
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.sex)
        )

        options.forEach { text ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = text == selectedOption.value,
                    onClick = { selectedOption.value = text }
                )

                Text(
                    text = text,
                    color = Color.Gray,
                    modifier = Modifier.clickable { selectedOption.value = text })
            }
        }
    }
}

@Composable
fun DateOfBirthComponent(mDate: MutableState<String>) {
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int


    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()


    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
    ) {
        IconComponent(Icons.Default.Cake)
        Text(
            fontWeight = FontWeight.Bold,
            text = mDate.value
        )
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            onClick = {
                mDatePickerDialog.show()
            },
            colors = ButtonDefaults.buttonColors()
        ) {
            Text(text = stringResource(R.string.change), color = Color.White)
        }
    }
}

@Composable
fun ScholarGradeSpinner(
    selectedItem: MutableState<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        stringResource(R.string.elementary),
        stringResource(R.string.secondary),
        stringResource(R.string.university),
        stringResource(R.string.other)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
    ) {
        IconComponent(Icons.Default.School)
        Box(
            modifier = Modifier
                .width(250.dp)
        ) {
            Text(
                text = selectedItem.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .border(2.dp, Color.Gray)
                    .padding(16.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                items.forEach { label ->
                    DropdownMenuItem(onClick = {
                        onItemSelected(label)
                        expanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
        }
    }
}




