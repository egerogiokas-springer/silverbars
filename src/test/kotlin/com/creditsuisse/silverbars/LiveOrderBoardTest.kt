package com.creditsuisse.silverbars

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test

class LiveOrderBoardTest {

    @Test
    fun `can register an order`() {
        val liveOrderBoard = LiveOrderBoard()
        val userId = UserId("testUserId")
        val orderQuantity = OrderQuantity(1.1)
        val pricePerKilo = PricePerKilo(303)
        val orderType = OrderType.BUY
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
        val orderType = OrderType.BUY
        val orderId = liveOrderBoard.registerOrder(userId, orderQuantity, pricePerKilo, orderType);

        assertThat(liveOrderBoard.orders.size, equalTo(1))
        liveOrderBoard.cancelOrder(orderId)
        assertThat(liveOrderBoard.orders.size, equalTo(0))
    }

}