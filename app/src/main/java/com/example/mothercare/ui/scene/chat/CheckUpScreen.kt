package com.example.mothercare.ui.scene.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mothercare.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(horizontal = 6.dp),
                        text = "Checkout",
                        style = TextStyle(
//                color = Color.White,
                            fontWeight = FontWeight.W600,
                            fontSize = 19.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium))
                        )
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        },
                        painter = painterResource(id = com.google.android.material.R.drawable.ic_arrow_back_black_24),
                        contentDescription = null
                    )
                }
            )
        }
    ) {

        CheckoutForm(modifier.padding(it))
    }

}



@Preview(showSystemUi = true)
@Composable
fun CheckoutForm(
    modifier: Modifier = Modifier,
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Unlock All features with a Premium Account starting at just NGN 250/mo",
                style = TextStyle(
//                color = Color.White,
                    fontWeight = FontWeight.W600,
                    fontSize = 19.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Card Details",
                modifier = Modifier.align(Alignment.Start),
                style = TextStyle(
//                color = Color.White,
                    fontWeight = FontWeight.W600,
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium))
                )
            )

//            Text(text = "Card Number")
            EditTextField(onValueChange = {}, value = "", label = "CARD Number")
            Spacer(modifier = Modifier.height(4.dp))
            EditTextField(onValueChange = {}, value = "", label = "MM")
            Spacer(modifier = Modifier.height(4.dp))
            EditTextField(onValueChange = {}, value = "", label = "YY")
            Spacer(modifier = Modifier.height(4.dp))
            EditTextField(onValueChange = {}, value = "", label = "CVV")
            Spacer(modifier = Modifier.height(4.dp))
            EditTextField(onValueChange = {}, value = "", label = "CARD Pin")

            Spacer(modifier = Modifier.height(30.dp))
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "Total:",
                    style = TextStyle(
//                color = Color.White,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium))
                    )
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "NGN 250/mo",
                    style = TextStyle(
//                color = Color.White,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium))
                    )
                )
            }
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {

                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Blue,
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Pay",
                        modifier = Modifier.padding(10.dp),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.W600,
//                        fontSize = 17.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    )
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
//                        contentDescription = null,
//                        tint = Color(0xFFFFFFFF)
//                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    label: String,
    maxLines: Int = 1,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        maxLines = maxLines,
        label = { Text(text = label) },
        keyboardActions = KeyboardActions.Default,
    )
}
