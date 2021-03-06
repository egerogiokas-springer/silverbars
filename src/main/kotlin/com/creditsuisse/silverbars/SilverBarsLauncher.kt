package com.creditsuisse.silverbars

import java.util.*

data class OrderSummary(val orderQuantity: OrderQuantity, val pricePerKilo: PricePerKilo, val listOfOrderIds: List<OrderId>)

data class Order(val userId: UserId, val orderQuantity: OrderQuantity, val pricePerKilo: PricePerKilo, val orderType: OrderType, val id: OrderId = OrderId(UUID.randomUUID().toString()))

class LiveOrderBoard {
    val orders: MutableList<Order> = mutableListOf()

    fun registerOrder(userId: UserId, orderQuantity: OrderQuantity, pricePerKilo: PricePerKilo, orderType: OrderType): OrderId {
        val order = Order(userId, orderQuantity, pricePerKilo, orderType)
        this.orders.add(order)
        return order.id
    }

    fun cancelOrder(orderId: OrderId) {
        this.orders.removeIf({ it.id == orderId })
    }

    fun getSellSummaryInformation(): List<OrderSummary> {
        return orders.filter { it.orderType == OrderType.SELL }
                .groupBy { it.pricePerKilo }
                .map { entry -> OrderSummary(OrderQuantity(reduceOrderQuantity(entry)), entry.key, entry.value.map { it.id }) }
                .sortedBy { it.pricePerKilo.price }
    }

    fun getBuySummaryInformation(): List<OrderSummary> {
        return orders.filter { it.orderType == OrderType.BUY }
                .groupBy { it.pricePerKilo }
                .map { entry -> OrderSummary(OrderQuantity(reduceOrderQuantity(entry)), entry.key, entry.value.map { it.id }) }
                .sortedByDescending { it.pricePerKilo.price }
    }

    private fun reduceOrderQuantity(entry: Map.Entry<PricePerKilo, List<Order>>) =
            entry.value.map { it.orderQuantity.amount }.reduce { acc, amount -> amount + acc }

}

data class OrderId(val id: String)

enum class OrderType { BUY, SELL }

data class OrderQuantity(val amount: Double)

data class PricePerKilo(val price: Int)

data class UserId(val userId: String)
