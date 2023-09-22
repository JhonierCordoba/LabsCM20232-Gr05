package co.edu.udea.compumovil.gr05_20232.lab1

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.MobileFriendly
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Signpost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

/*
class ContactDataActivity : AppCompatActivity() {
    //val viewModel by viewModels<CitiesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Labs20232Gr05Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    ContactDataContent()
                }
            }
        }
    }
}
*/


@Composable
fun ContactDataContent(navController: NavController) {
    val title = stringResource(R.string.contact_info)
    val phoneText = stringResource(R.string.phone)
    val emailText = stringResource(R.string.email)
    val addressText = stringResource(R.string.address)
    val countryText = stringResource(R.string.country)
    val cityText = stringResource(R.string.city)


    var phone by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    val country = rememberSaveable { mutableStateOf("") }
    val city = rememberSaveable { mutableStateOf("") }

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            ConstraintLayout {
                val (form) = createRefs()
                Column(modifier = Modifier.constrainAs(form) {
                    top.linkTo(parent.top)
                }
                ) {
                    Title(title)
                    DividerComponent()
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        CountryField(country)
                        CityField(city)
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        PhoneFiled(phone, onLastNameChange = { phone = it })
                        EmailFiled(email, onLastNameChange = { email = it })
                    }
                    AddressFiled(address = address, onLastNameChange = { address = it })
                }
            }
        }

        else -> {
            ConstraintLayout {
                val (form, button) = createRefs()
                Column(modifier = Modifier.constrainAs(form) {
                    top.linkTo(parent.top)
                }
                ) {
                    Title(title)
                    DividerComponent()
                    CountryField(country)
                    CityField(city)
                    PhoneFiled(phone, onLastNameChange = { phone = it })
                    EmailFiled(email, onLastNameChange = { email = it })
                    AddressFiled(address = address, onLastNameChange = { address = it })
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
                if (phone.isNotEmpty() && email.isNotEmpty() && country.value.isNotEmpty()) {
                    Log.i(
                        "PersonalData",
                        "$title: " +
                                "\n$phoneText: $phone" +
                                if (address.isNotEmpty()) {
                                    "\n$addressText: $address"
                                } else {
                                    ""
                                }
                                + "\n$emailText: $email "
                                + "\n$countryText: ${country.value}" +
                                if (city.value.isNotEmpty()) {
                                    "\n$cityText: ${city.value}"
                                } else {
                                    ""
                                }
                    )
                    navController.popBackStack()
                }
            },
            Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colors.primary),
            ) {
            Text(stringResource(R.string.next))
        }
    }
}

@Composable
fun PhoneFiled(
    phoneNumber: String,
    onLastNameChange: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp)
    ) {
        IconComponent(Icons.Default.MobileFriendly, true)
        TextFieldComponent(
            text = phoneNumber,
            onTextChange = onLastNameChange,
            labelText = stringResource(R.string.phone),
            keyboardType = KeyboardType.Phone,
            true
        )
    }
}

@Composable
fun EmailFiled(
    email: String,
    onLastNameChange: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp)
    ) {
        IconComponent(Icons.Default.Mail, true)
        TextFieldComponent(
            text = email,
            onTextChange = onLastNameChange,
            labelText = stringResource(R.string.email),
            keyboardType = KeyboardType.Email,
            true
        )
    }
}

@Composable
fun CountryField(category: MutableState<String>) {
    Row(
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp)
    ) {
        IconComponent(Icons.Default.Public, true)
        AutoComplete(
            listOf(
                "Colombia",
                "Argentina",
                "Brazil",
                "Chile",
                "Ecuador",
                "Peru",
                "Venezuela"
            ),
            "${stringResource(R.string.country)}*", category
        )
    }
}

@Composable
fun CityField(category: MutableState<String>) {
    Row(
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp)
    ) {
        IconComponent(Icons.Default.LocationCity, true)
        AutoComplete(
            listOf(
                "Medellin",
                "Bogota",
                "Cali",
                "Barranquilla",
                "Cartagena",
                "Bucaramanga",
                "Pereira",
                "Manizales",
                "Armenia",
                "Ibague",
                "Cucuta",
                "Pasto",
                "Neiva",
                "Villavicencio",
                "Santa Marta",
                "Valledupar",
                "Monteria",
                "Popayan",
                "Sincelejo",
                "Tunja",
                "Riohacha",
                "Florencia",
                "Quibdo",
                "Yopal",
                "Mocoa",
                "San Andres",
                "Leticia",
                "Puerto Carreño",
                "Arauca",
                "Inirida",
                "Mitú",
                "San Jose del Guaviare"
            ),
            stringResource(R.string.city),
            category
        )
    }
}

@Composable
fun AddressFiled(
    address: String,
    onLastNameChange: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp)
    ) {
        IconComponent(Icons.Default.Signpost, true)
        TextFieldComponent(
            text = address,
            onTextChange = onLastNameChange,
            labelText = stringResource(R.string.address),
            keyboardType = KeyboardType.Text
        )
    }
}


/*
@Preview
@Composable
fun ShowContactData(){
    ContactDataContent()
}
 */