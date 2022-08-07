package model

import model.interfaces.IPriceRule

// Price rule for x-for-y units strategy
// e.g.: 2-for-1 voucher
class XforYRule(
    override val productCode: String,
    private val x: Int,
    private val y: Int
    ) : IPriceRule {
    override fun getTotalPriceForProducts(products: List<Product>): Double {
        if (products.isEmpty()) {
            return 0.0
        }

        return ((products.size / x) * y + (products.size % x)) * products[0].price
    }
}