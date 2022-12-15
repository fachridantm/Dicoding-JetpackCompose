package com.dicoding.bangkitmerchstore.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.bangkitmerchstore.R
import com.dicoding.bangkitmerchstore.ui.theme.BangkitMerchStoreTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(8.dp).fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.profile_photo),
            contentDescription = stringResource(R.string.profile_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Fachridan Tio Mu'afa",
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)

        )
        Text(
            text = "fachridantm@gmail.com",
            style = MaterialTheme.typography.subtitle1,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    BangkitMerchStoreTheme {
        ProfileScreen()
    }
}