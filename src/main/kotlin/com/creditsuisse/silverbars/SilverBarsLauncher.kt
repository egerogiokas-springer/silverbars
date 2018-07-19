package com.creditsuisse.silverbars

import java.util.*


fun main(args: Array<String>) {
    val liveOrderBoard = LiveOrderBoard()
}

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

}
data class OrderId (val id: String)

enum class OrderType { BUY, SELL }

data class OrderQuantity(val amount: Double)

data class PricePerKilo(val price: Int)

data class UserId(val userId: String)
