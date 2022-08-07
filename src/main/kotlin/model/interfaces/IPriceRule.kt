package model.interfaces

import model.Product

interface IPriceRule {
    val productCode: String

    fun getTotalPriceForProducts(products: List<Product>): Double
}