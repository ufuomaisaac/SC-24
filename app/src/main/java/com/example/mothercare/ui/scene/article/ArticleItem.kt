package com.example.mothercare.ui.scene.article

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mothercare.ui.scene.auth.signin.SignInTopAppBar

@Composable
fun ArticleItem(modifier: Modifier = Modifier, @DrawableRes imageIdRes: Int,
                @StringRes firstTextIdRes: Int, topic: String, navController: NavController){
    Column() {
        SignInTopAppBar(topAppBarTitle = topic, NavUp = {navController.popBackStack()})
        LazyColumn() {

            item {
                Image(
                    painter = painterResource(id = imageIdRes),
                    contentDescription = "Artical image",
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = topic,
                    modifier.padding(16.dp),
                    fontSize = 24.sp,
                    //textAlign = TextAlign.Justify
                )
                /*
                            Text(
                                text = stringResource(id = R.string.first_text),
                                modifier.padding(16.dp),
                                textAlign = TextAlign.Justify
                            )
                */

                Text(
                    text = stringResource(id = firstTextIdRes),
                    modifier.padding(16.dp),
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(64.dp))

                Text(text = "yacohchuk/Getty Images",
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(Color.LightGray))

                Spacer(modifier = Modifier.height(64.dp))
            }
        }

    }


}
