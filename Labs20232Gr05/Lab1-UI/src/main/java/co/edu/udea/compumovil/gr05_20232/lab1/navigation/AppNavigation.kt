package co.edu.udea.compumovil.gr05_20232.lab1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr05_20232.lab1.ContactDataContent
import co.edu.udea.compumovil.gr05_20232.lab1.PersonalData

@Composable
fun AppNavigarion(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.PersonalDataScreen.route){
        composable(route = AppScreens.PersonalDataScreen.route){
            PersonalData(navController)
        }

        composable(route = AppScreens.ContactDataScreen.route){
            ContactDataContent(navController)
        }
    }
}