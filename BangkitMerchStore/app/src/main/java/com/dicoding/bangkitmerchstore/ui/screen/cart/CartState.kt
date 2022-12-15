package com.dicoding.bangkitmerchstore.ui.screen.cart

import com.dicoding.bangkitmerchstore.model.OrderMerch

data class CartState(
    val orderMerch: List<OrderMerch>,
    val totalRequiredPoint: Int
)
