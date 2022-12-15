package com.dicoding.bangkitmerchstore.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.bangkitmerchstore.di.Injection
import com.dicoding.bangkitmerchstore.model.OrderMerch
import com.dicoding.bangkitmerchstore.ui.ViewModelFactory
import com.dicoding.bangkitmerchstore.ui.common.StateHolder
import com.dicoding.bangkitmerchstore.ui.components.MerchItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.stateHolder.collectAsState(initial = StateHolder.Loading).value.let { stateHolder ->
        when (stateHolder) {
            is StateHolder.Loading -> {
                viewModel.getAllMerch()
            }
            is StateHolder.Success -> {
                HomeContent(
                    orderMerch = stateHolder.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is StateHolder.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderMerch: List<OrderMerch>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(orderMerch) { data ->
            MerchItem(
                image = data.merch.image,
                title = data.merch.title,
                requiredPoint = data.merch.requiredPoint,
                modifier = Modifier.clickable {
                    navigateToDetail(data.merch.id)
                }
            )
        }
    }
}