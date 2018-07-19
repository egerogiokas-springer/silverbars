package com.creditsuisse.silverbars

import com.creditsuisse.silverbars.OrderType.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class LiveOrderBoardTest {

    @Test
    fun `can register an order`() {
        val liveOrderBoard = LiveOrderBoard()
        val userId = UserId("testUserId")
        val orderQuantity = OrderQuantity(1.1)
        val pricePerKilo = PricePerKilo(303)
        val orderType = BUY
        liveOrderBoard.registerOrder(userId, orderQuantity, pricePerKilo, orderType)

        assertThat(liveOrderBoard.orders[0].userId, equalTo(userId))
        assertThat(liveOrderBoard.orders[0].orderQuantity, equalTo(orderQuantity))
        assertThat(liveOrderBoard.orders[0].pricePerKilo, equalTo(pricePerKilo))
        assertThat(liveOrderBoard.orders[0].orderType, equalTo(orderType))
    }

    @Test
    fun `can cancel an order`() {
        val liveOrderBoard = LiveOrderBoard()
        val userId = UserId("testUserId")
        val orderQuantity = OrderQuantity(1.1)
        val pricePerKilo = PricePerKilo(303)
        val orderType = BUY
        val orderId = liveOrderBoard.registerOrder(userId, orderQuantity, pricePerKilo, orderType)

        assertThat(liveOrderBoard.orders.size, equalTo(1))
        liveOrderBoard.cancelOrder(orderId)
        assertThat(liveOrderBoard.orders.size, equalTo(0))
    }

    @Test
    fun `can display SELL orders summary correctly`() {
        val liveOrderBoard = LiveOrderBoard()
        val orderIdA = liveOrderBoard.registerOrder(UserId("user1"), OrderQuantity(3.5), PricePerKilo(306), SELL)
        val orderIdB = liveOrderBoard.registerOrder(UserId("user2"), OrderQuantity(1.2), PricePerKilo(310), SELL)
        val orderIdC = liveOrderBoard.registerOrder(UserId("user3"), OrderQuantity(1.5), PricePerKilo(307), SELL)
        val orderIdD = liveOrderBoard.registerOrder(UserId("user4"), OrderQuantity(2.0), PricePerKilo(306), SELL)

        val sellSummaryInformation = liveOrderBoard.getSellSummaryInformation()
        assertThat(sellSummaryInformation[0], equalTo(OrderSummary(OrderQuantity(5.5), PricePerKilo(306), listOf(orderIdA, orderIdD))))
        assertThat(sellSummaryInformation[1], equalTo(OrderSummary(OrderQuantity(1.5), PricePerKilo(307), listOf(orderIdC))))
        assertThat(sellSummaryInformation[2], equalTo(OrderSummary(OrderQuantity(1.2), PricePerKilo(310), listOf(orderIdB))))
    }

    @Test
    fun `can display BUY orders summary correctly`() {
        val liveOrderBoard = LiveOrderBoard()
        val orderIdA = liveOrderBoard.registerOrder(UserId("user1"), OrderQuantity(3.5), PricePerKilo(306), BUY)
        val orderIdB = liveOrderBoard.registerOrder(UserId("user2"), OrderQuantity(1.2), PricePerKilo(310), BUY)
        val orderIdC = liveOrderBoard.registerOrder(UserId("user3"), OrderQuantity(1.5), PricePerKilo(307), BUY)
        val orderIdD = liveOrderBoard.registerOrder(UserId("user4"), OrderQuantity(2.0), PricePerKilo(306), BUY)

        val sellSummaryInformation = liveOrderBoard.getBuySummaryInformation()
        assertThat(sellSummaryInformation[0], equalTo(OrderSummary(OrderQuantity(1.2), PricePerKilo(310), listOf(orderIdB))))
        assertThat(sellSummaryInformation[1], equalTo(OrderSummary(OrderQuantity(1.5), PricePerKilo(307), listOf(orderIdC))))
        assertThat(sellSummaryInformation[2], equalTo(OrderSummary(OrderQuantity(5.5), PricePerKilo(306), listOf(orderIdA, orderIdD))))
    }

}