package com.dicoding.bangkitmerchstore.data

import com.dicoding.bangkitmerchstore.model.FakeMerchDataSource
import com.dicoding.bangkitmerchstore.model.OrderMerch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class BangkitRepository {

    private val listOrder = mutableListOf<OrderMerch>()

    init {
        if (listOrder.isEmpty()) {
            FakeMerchDataSource.dummyMerch.forEach {
                listOrder.add(OrderMerch(it, 0))
            }
        }
    }

    fun getAllMerch(): Flow<List<OrderMerch>> {
        return flowOf(listOrder)
    }

    fun getOrderMerchById(merchId: Long): OrderMerch {
        return listOrder.first {
            it.merch.id == merchId
        }
    }

    fun updateOrderMerch(merchId: Long, newCountValue: Int): Flow<Boolean> {
        val index = listOrder.indexOfFirst { it.merch.id == merchId }
        val result = if (index >= 0) {
            val orderMerch = listOrder[index]
            listOrder[index] = orderMerch.copy(merch = orderMerch.merch, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderMerch(): Flow<List<OrderMerch>> {
        return getAllMerch()
            .map { list ->
                list.filter { orderMerch ->
                    orderMerch.count != 0
                }
            }
    }

    fun findMerch(query: String): Flow<List<OrderMerch>> {
        return getAllMerch()
            .map { list ->
                list.filter {
                    it.merch.title.contains(query, ignoreCase = true)
                }
            }
    }

    companion object {
        @Volatile
        private var instance: BangkitRepository? = null

        fun getInstance(): BangkitRepository =
            instance ?: synchronized(this) {
                BangkitRepository().apply {
                    instance = this
                }
            }
    }
}