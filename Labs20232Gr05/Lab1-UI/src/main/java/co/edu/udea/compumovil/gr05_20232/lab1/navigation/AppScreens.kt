package co.edu.udea.compumovil.gr05_20232.lab1.navigation

sealed class AppScreens(val route: String){
    object PersonalDataScreen: AppScreens("personal_data_screen")
    object ContactDataScreen: AppScreens("contact_data_screen")
}
