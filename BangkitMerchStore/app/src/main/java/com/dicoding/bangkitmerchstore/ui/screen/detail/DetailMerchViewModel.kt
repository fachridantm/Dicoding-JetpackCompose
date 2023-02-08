package com.dicoding.bangkitmerchstore.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bangkitmerchstore.data.BangkitRepository
import com.dicoding.bangkitmerchstore.model.Merch
import com.dicoding.bangkitmerchstore.model.OrderMerch
import com.dicoding.bangkitmerchstore.ui.common.StateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailMerchViewModel(private val repository: BangkitRepository) : ViewModel() {
    private val _stateDetail: MutableStateFlow<StateHolder<OrderMerch>> =
        MutableStateFlow(StateHolder.Loading)
    val stateDetail: StateFlow<StateHolder<OrderMerch>>
        get() = _stateDetail

    fun getMerchById(merchId: Long) {
        viewModelScope.launch {
            _stateDetail.value = StateHolder.Loading
            _stateDetail.value = StateHolder.Success(repository.getOrderMerchById(merchId))
        }
    }

    fun addToCart(merch: Merch, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMerch(merch.id, count)
        }
    }
}