package com.dicoding.bangkitmerchstore.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    private val _stateHome: MutableStateFlow<StateHolder<List<OrderMerch>>> =
        MutableStateFlow(StateHolder.Loading)
    val stateHome: StateFlow<StateHolder<List<OrderMerch>>>
        get() = _stateHome

    fun getAllMerch() {
        viewModelScope.launch {
            repository.getAllMerch()
                .catch {
                    _stateHome.value = StateHolder.Error(it.message.toString())
                }
                .collect { orderMerch ->
                    _stateHome.value = StateHolder.Success(orderMerch)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun findMerch(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            repository.findMerch(_query.value)
                .catch {
                    _stateHome.value = StateHolder.Error(it.message.toString())
                }
                .collect { merch ->
                    _stateHome.value = StateHolder.Success(merch)
                }
        }
    }
}