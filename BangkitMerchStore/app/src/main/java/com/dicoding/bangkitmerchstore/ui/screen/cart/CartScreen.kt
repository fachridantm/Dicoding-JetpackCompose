package com.dicoding.bangkitmerchstore.ui.screen.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.bangkitmerchstore.R
import com.dicoding.bangkitmerchstore.di.Injection
import com.dicoding.bangkitmerchstore.ui.ViewModelFactory
import com.dicoding.bangkitmerchstore.ui.common.StateHolder
import com.dicoding.bangkitmerchstore.ui.components.CartItem
import com.dicoding.bangkitmerchstore.ui.components.OrderButton

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    onOrderButtonClicked: (String) -> Unit,
) {
    viewModel.stateHolder.collectAsState(initial = StateHolder.Loading).value.let { stateHolder ->
        when (stateHolder) {
            is StateHolder.Loading -> {
                viewModel.getAddedOrderMerch()
            }
            is StateHolder.Success -> {
                if (stateHolder.data.orderMerch.isEmpty()) {
                    EmptyCart()
                } else {
                    CartContent(
                        stateHolder.data,
                        onProductCountChanged = { merchId, count ->
                            viewModel.updateOrderMerch(merchId, count)
                        },
                        onOrderButtonClicked = onOrderButtonClicked
                    )
                }
            }
            is StateHolder.Error -> {}
        }
    }
}

@Composable
fun EmptyCart() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.cart_empty),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CartContent(
    state: CartState,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shareMessage = stringResource(
        R.string.share_message,
        state.orderMerch.count(),
        state.totalRequiredPoint
    )
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Text(
                text = stringResource(R.string.menu_cart),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        OrderButton(
            text = stringResource(R.string.total_order, state.totalRequiredPoint),
            enabled = state.orderMerch.isNotEmpty(),
            onClick = {
                onOrderButtonClicked(shareMessage)
            },
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.orderMerch, key = { it.merch.id }) { item ->
                CartItem(
                    merchId = item.merch.id,
                    image = item.merch.image,
                    title = item.merch.title,
                    totalPoint = item.merch.requiredPoint * item.count,
                    count = item.count,
                    onProductCountChanged = onProductCountChanged,
                )
                Divider()
            }
        }
    }
}