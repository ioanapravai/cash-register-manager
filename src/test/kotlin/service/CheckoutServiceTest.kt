package service

import helper.Consts
import model.Product
import model.StandardPriceRule
import model.UnitThresholdPriceRule
import model.XforYRule
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CheckoutServiceTest {

    @Test
    fun scan() {
        // Arrange
        val voucherRule = XforYRule(Consts.VOUCHER_CODE, 2, 1)
        val tshirtRule = UnitThresholdPriceRule(Consts.TSHIRT_CODE, 3, 19.0)
        val pantsStandardRule = StandardPriceRule(Consts.PANTS_CODE)

        val giftCard = Product(Consts.VOUCHER_CODE, "Gift Card", 5.0)
        val summerTShirt = Product(Consts.TSHIRT_CODE, "Summer T-Shirt", 20.0)
        val summerPants = Product(Consts.PANTS_CODE, "Summer Pants", 7.50)

        var total = 0.0

        val checkoutService = CheckoutService(listOf(voucherRule, tshirtRule, pantsStandardRule))

        // Assert
        checkoutService.scan(giftCard)
        checkoutService.scan(summerTShirt)
        checkoutService.scan(giftCard)
        checkoutService.scan(giftCard)
        checkoutService.scan(summerPants)
        checkoutService.scan(summerTShirt)
        total = checkoutService.scan(summerTShirt)

        assertEquals(74.5, total)

        checkoutService.clearProducts()

        checkoutService.scan(giftCard)
        checkoutService.scan(summerTShirt)
        total = checkoutService.scan(summerPants)

        assertEquals(32.5, total)


        checkoutService.clearProducts()

        checkoutService.scan(giftCard)
        checkoutService.scan(summerTShirt)
        total = checkoutService.scan(giftCard)

        assertEquals(25.0, total)


        checkoutService.clearProducts()

        checkoutService.scan(summerTShirt)
        checkoutService.scan(summerTShirt)
        checkoutService.scan(summerTShirt)
        checkoutService.scan(giftCard)
        total = checkoutService.scan(summerTShirt)

        assertEquals(81.0, total)
    }
}