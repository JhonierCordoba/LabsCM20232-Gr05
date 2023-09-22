package co.edu.udea.compumovil.gr05_20232.lab1

import android.content.res.Configuration
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr05_20232.lab1.navigation.AppScreens
import co.edu.udea.compumovil.gr05_20232.lab1.retrofitImpl.CitiesViewModel
import co.edu.udea.compumovil.gr05_20232.lab1.ui.theme.Labs20232Gr05Theme
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MobileFriendly
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr05_20232.lab1.retrofitImpl.Cities
import co.edu.udea.compumovil.gr05_20232.lab1.retrofitImpl.Constants
import co.edu.udea.compumovil.gr05_20232.lab1.retrofitImpl.Country
import co.edu.udea.compumovil.gr05_20232.lab1.retrofitImpl.RetrofitClient
import co.edu.udea.compumovil.gr05_20232.lab1.retrofitImpl.WebService
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
fun ContactDataContent(navController: NavController){

    val configuration = LocalConfiguration.current
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
                ){
                    TelefonFiled()
                    AutoComplete()
                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Volver atras")
        }
    }
}

private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://countriesnow.space/api/v0.1/countries/cities")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

@Composable
fun CountryGradeSpinner(){
    var expanded by remember { mutableStateOf(false) }
    val country = stringResource(R.string.country)
    val items = listOf("Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Costa Rica", "Cuba", "Ecuador", "Mexico")
    var selectedItem by remember { mutableStateOf(country) }

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

@Composable
fun TelefonFiled() {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp)) {
        var numberValue by remember {mutableStateOf("")}
        Icon(imageVector = Icons.Default.MobileFriendly, contentDescription = null)
        Spacer(modifier = Modifier.width(10.dp))
        TextField(
            value = numberValue,
            onValueChange = { numberValue = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Number Keyboard Type") }
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