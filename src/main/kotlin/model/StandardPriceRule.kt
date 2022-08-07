package model

import model.interfaces.IPriceRule

class StandardPriceRule(override val productCode: String) : IPriceRule {
    override fun getTotalPriceForProducts(products: List<Product>): Double {
        if(products.isEmpty()){
            return 0.0
        }

        return products.size * products[0].price
    }
}