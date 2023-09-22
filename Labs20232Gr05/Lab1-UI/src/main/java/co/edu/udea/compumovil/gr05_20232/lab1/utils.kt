package co.edu.udea.compumovil.gr05_20232.lab1



import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun AutoComplete(dropdownItems :List<String>, name: String, category: MutableState<String>) {
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    // Category Field
    Column(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {

        Text(
            modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
            text = name,
            fontSize = 16.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )

        Column {

            Row {
                TextField(
                    modifier = Modifier
                        .width(250.dp)
                        .border(
                            width = 1.8.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    value = category.value,
                    onValueChange = {
                        category.value = it
                        expanded = true
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Gray
                    ),
                    textStyle = TextStyle(
                        color = Color.Gray,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = Color.Gray
                            )
                        }
                    }
                )
            }

            AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .width(250.dp),
                    elevation = 15.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {

                    LazyColumn(
                        modifier = Modifier.heightIn(max = 150.dp),
                    ) {

                        if (category.value.isNotEmpty()) {
                            items(
                                dropdownItems.filter {
                                    it.lowercase()
                                        .contains(category.value.lowercase()) || it.lowercase()
                                        .contains("others")
                                }
                                    .sorted()
                            ) {
                                CategoryItems(title = it) { title ->
                                    category.value = title
                                    expanded = false
                                }
                            }
                        } else {
                            items(
                                dropdownItems.sorted()
                            ) {
                                CategoryItems(title = it) { title ->
                                    category.value = title
                                    expanded = false
                                }
                            }
                        }

                    }

                }
            }

        }

    }
}

@Composable
fun IconComponent(icon: ImageVector,
                  textFielIcon: Boolean = false) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        Modifier.padding(top = if (textFielIcon) 20.dp else 0.dp, end = 10.dp)
    )
}

@Composable
fun TextFieldComponent(
    text: String,
    onTextChange: (String) -> Unit,
    labelText: String,
    keyboardType: KeyboardType,
    required: Boolean = false,
) {
    Column{
        Text(
            modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
            text = if (required) "$labelText*" else labelText,
            fontSize = 15.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        TextField(
            value = text,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Next),
            onValueChange = onTextChange,
            modifier = Modifier
                .width(250.dp)
                .border(1.8.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Gray
            ),
            singleLine = true
        )
    }
}

@Composable
fun DividerComponent() {
    Divider(
        color = Color.Gray,
        thickness = 1.dp,
    )
}

@Composable
fun CategoryItems(
    title: String,
    onSelect: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .width(250.dp)
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }

}













