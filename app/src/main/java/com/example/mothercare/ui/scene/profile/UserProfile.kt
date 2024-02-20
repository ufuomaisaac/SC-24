package com.example.mothercare.ui.scene.profile

import android.graphics.Paint.Style
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mothercare.R
import com.example.mothercare.theme.MotherCareTheme
import com.example.mothercare.ui.scene.auth.signin.SignInContent
import com.example.mothercare.ui.scene.auth.signin.SignInTopAppBar
import com.example.mothercare.ui.scene.auth.signin.TextButton

@Composable
fun UserProfile() {
    Column {
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Articles",
            modifier = Modifier.padding(horizontal = 16.dp)
            )

        ClickableTextAndImage(TextId = 1234, ImageId = , onItemClicked = { /*TODO*/ }, briefText = "Health is wealth, knowing what to do is essential and good, life boils down to getting either you win to learn from your experience you can never lose...")

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
){
    Column(modifier =
    modifier.padding(horizontal = 16.dp)) {
        TextField(
            value = " ",
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search , contentDescription = "search" )
            },
            onValueChange = {},
            colors = TextFieldDefaults.textFieldColors(
            ),
            placeholder = {
                Text(text = "Search")
            },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        )
    }
}

@Composable
fun FavoriteCardCollection(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    @StringRes textId: Int
) {

    Surface(
        shape = androidx.compose.material3.MaterialTheme.shapes.medium,
        color = androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .padding(8.dp)

    ) {

        Row(
            modifier = modifier
                .width(255.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(id = textId),

            )
        }
    }
}


@Composable
fun ClickableTextAndImage(@StringRes TextId : Int, @DrawableRes ImageId: Int, onItemClicked: () -> Unit, briefText: String) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ab3_stretching),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable(onClick = onItemClicked),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = briefText,
            modifier = Modifier.clickable(onClick = onItemClicked)
        )
    }
}



@Preview
@Composable
fun ProfilePreview() {
    MotherCareTheme {
        Surface {
           /* ClickableTextAndImage(onItemClicked = { *//*TODO*//* }, briefText = "Health is wealth, knowing what to do is essential and good," +
                    " life boils down to getting either you win to learn from your experience," +
                    " you can never lose...", )*/
            UserProfile()
        }
    }
}
