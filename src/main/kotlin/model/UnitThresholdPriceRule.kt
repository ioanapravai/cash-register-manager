package model

import model.interfaces.IPriceRule

// Price rule for multiple units strategy
// e.g.: If you buy 3 or more t-shirt items, the price per unit should be 19.00€
class UnitThresholdPriceRule(
    override val productCode: String,
    private val threshold: Int,
    private val pricePerUnit: Double
    ) : IPriceRule {
    override fun getTotalPriceForProducts (products: List<Product>): Double {
        if (products.isEmpty()) {
            return 0.0
        }

        if (products.size >= threshold) {
            return products.size * pricePerUnit
        }

        return products.size * products[0].price
    }
}