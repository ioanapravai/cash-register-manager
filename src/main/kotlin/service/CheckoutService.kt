package service

import helper.Consts
import model.Product
import model.interfaces.ICheckoutService
import model.interfaces.IPriceRule

class CheckoutService (val pricingRules: List<IPriceRule>) : ICheckoutService {
    private val vouchers: MutableList<Product> = mutableListOf()
    private val tshirts: MutableList<Product> = mutableListOf()
    private val pants: MutableList<Product> = mutableListOf()

    // Scan the product and return the current total price
    // of all products scanned until now.
    override fun scan(product: Product): Double {
        when (product.code) {
            Consts.VOUCHER_CODE -> vouchers.add(product)
            Consts.TSHIRT_CODE -> tshirts.add(product)
            Consts.PANTS_CODE -> pants.add(product)
            else -> {
                println("Unexpected product")
            }
        }

        var totalPrice = 0.0

        pricingRules.forEach {
            when (it.productCode) {
                Consts.VOUCHER_CODE -> totalPrice += it.getTotalPriceForProducts(vouchers)
                Consts.TSHIRT_CODE -> totalPrice += it.getTotalPriceForProducts(tshirts)
                Consts.PANTS_CODE -> totalPrice += it.getTotalPriceForProducts(pants)
            }
        }

        return totalPrice
    }

    fun clearProducts(){
        vouchers.clear()
        tshirts.clear()
        pants.clear()
    }
}