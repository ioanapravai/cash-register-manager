import helper.Consts
import model.Product
import model.StandardPriceRule
import model.UnitThresholdPriceRule
import model.XforYRule
import service.CheckoutService

fun main(args: Array<String>) {
    val pricingRule1 = XforYRule(Consts.VOUCHER_CODE, 2, 1)
    val pricingRule2 = UnitThresholdPriceRule(Consts.TSHIRT_CODE, 3, 19.0)
    val standardPrice = StandardPriceRule(Consts.PANTS_CODE)

    val giftCard = Product(Consts.VOUCHER_CODE, "Gift Card", 5.0)
    val summerTShirt = Product(Consts.TSHIRT_CODE, "Summer T-Shirt", 20.0)
    val summerPants = Product(Consts.PANTS_CODE, "Summer Pants", 7.50)

    var total = 0.0

    val checkoutService = CheckoutService(listOf(pricingRule1, pricingRule2, standardPrice))

    total = checkoutService.scan(giftCard)
    println("total: ")
    println(total)

    total = checkoutService.scan(summerTShirt)
    println("total: ")
    println(total)

    total = checkoutService.scan(giftCard)
    println("total: ")
    println(total)

    total = checkoutService.scan(giftCard)
    println("total: ")
    println(total)

    total = checkoutService.scan(summerPants)
    println("total: ")
    println(total)

    total = checkoutService.scan(summerTShirt)
    println("total: ")
    println(total)

    total = checkoutService.scan(summerTShirt)
    println("total: ")
    println(total)
}