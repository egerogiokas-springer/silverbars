package com.creditsuisse.silverbars


fun main(args: Array<String>) {
    val liveOrderBoard = LiveOrderBoard()
}

class Order(userId: UserId, orderQuantity: OrderQuantity, pricePerKilo: PricePerKilo, buy: OrderType) {

}

class LiveOrderBoard {
    private val orders: MutableList<Order> = mutableListOf()

    fun registerOrder(order: Order) {
        this.orders.add(order)
    }
}

enum class OrderType { BUY, SELL }

class OrderQuantity(amount: Double) {

}

class PricePerKilo(price: Int) {

}

class UserId(userId: String) {

}
