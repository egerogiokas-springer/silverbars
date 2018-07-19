package com.creditsuisse.silverbars

import org.junit.Test

class LiveOrderBoardTest {

    @Test
    fun `test can register an order`(){
        val liveOrderBoard = LiveOrderBoard()
        liveOrderBoard.registerOrder(Order(UserId("testUserId"), OrderQuantity(1.1), PricePerKilo(303), OrderType.BUY))
    }
}