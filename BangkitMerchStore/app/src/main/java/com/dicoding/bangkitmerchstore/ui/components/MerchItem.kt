package com.dicoding.bangkitmerchstore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.bangkitmerchstore.R
import com.dicoding.bangkitmerchstore.ui.theme.BangkitMerchStoreTheme
import com.dicoding.bangkitmerchstore.ui.theme.Shapes

@Composable
fun MerchItem(
    image: Int,
    title: String,
    requiredPoint: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.background(Color.LightGray).padding(8.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = stringResource(R.string.required_point, requiredPoint),
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MerchItemPreview() {
    BangkitMerchStoreTheme {
        MerchItem(R.drawable.bangkit_tumblr, "Digital Tumblr", 1500)
    }
}