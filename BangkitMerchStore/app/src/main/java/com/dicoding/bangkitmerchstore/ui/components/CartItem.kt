package com.dicoding.bangkitmerchstore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun CartItem(
    merchId: Long,
    image: Int,
    title: String,
    totalPoint: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(
                    R.string.required_point,
                    totalPoint
                ),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2,
            )
        }
        ProductCounter(
            orderId = merchId,
            orderCount = count,
            onProductIncreased = { onProductCountChanged(merchId, count + 1) },
            onProductDecreased = { onProductCountChanged(merchId, count - 1) },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    BangkitMerchStoreTheme {
        CartItem(
            1, R.drawable.bangkit_earbuds, "Exclusive earbuds", 6000, 0,
            onProductCountChanged = { _, _ -> },
        )
    }
}