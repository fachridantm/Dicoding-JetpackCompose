package com.dicoding.bangkitmerchstore.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bangkitmerchstore.data.BangkitRepository
import com.dicoding.bangkitmerchstore.ui.common.StateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository: BangkitRepository) : ViewModel() {
    private val _stateCart: MutableStateFlow<StateHolder<CartState>> =
        MutableStateFlow(StateHolder.Loading)
    val stateCart: StateFlow<StateHolder<CartState>>
        get() = _stateCart

    fun getAddedOrderMerch() {
        viewModelScope.launch {
            _stateCart.value = StateHolder.Loading
            repository.getAddedOrderMerch()
                .collect { orderMerch ->
                    val totalRequiredPoint =
                        orderMerch.sumOf { it.merch.requiredPoint * it.count }
                    _stateCart.value =
                        StateHolder.Success(CartState(orderMerch, totalRequiredPoint))
                }
        }
    }

    fun updateOrderMerch(merchId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMerch(merchId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderMerch()
                    }
                }
        }
    }
}