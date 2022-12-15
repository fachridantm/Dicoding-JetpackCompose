package com.dicoding.bangkitmerchstore.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.bangkitmerchstore.R
import com.dicoding.bangkitmerchstore.di.Injection
import com.dicoding.bangkitmerchstore.model.OrderMerch
import com.dicoding.bangkitmerchstore.ui.ViewModelFactory
import com.dicoding.bangkitmerchstore.ui.common.StateHolder
import com.dicoding.bangkitmerchstore.ui.components.MerchItem
import com.dicoding.bangkitmerchstore.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val query by viewModel.query

    viewModel.stateHolder.collectAsState(initial = StateHolder.Loading).value.let { stateHolder ->
        when (stateHolder) {
            is StateHolder.Loading -> {
                viewModel.getAllMerch()
            }
            is StateHolder.Success -> {
                Column {
                    SearchBar(
                        query = query,
                        onQueryChange = viewModel::findMerch,
                        modifier = modifier.background(color = MaterialTheme.colors.primary)
                    )
                    if (stateHolder.data.isEmpty()) {
                        NotFound()
                    } else {
                        HomeContent(
                            orderMerch = stateHolder.data,
                            modifier = modifier,
                            navigateToDetail = navigateToDetail,
                        )
                    }
                }
            }
            is StateHolder.Error -> {}
        }
    }
}

@Composable
fun NotFound() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.not_found),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
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