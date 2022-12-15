package com.dicoding.bangkitmerchstore.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bangkitmerchstore.data.BangkitRepository
import com.dicoding.bangkitmerchstore.model.OrderMerch
import com.dicoding.bangkitmerchstore.ui.common.StateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: BangkitRepository) : ViewModel() {
    private val _stateHolder: MutableStateFlow<StateHolder<List<OrderMerch>>> =
        MutableStateFlow(StateHolder.Loading)
    val stateHolder: StateFlow<StateHolder<List<OrderMerch>>>
        get() = _stateHolder

    fun getAllMerch() {
        viewModelScope.launch {
            repository.getAllMerch()
                .catch {
                    _stateHolder.value = StateHolder.Error(it.message.toString())
                }
                .collect { orderMerch ->
                    _stateHolder.value = StateHolder.Success(orderMerch)
                }
        }
    }
}