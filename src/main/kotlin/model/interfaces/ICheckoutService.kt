package model.interfaces

import model.Product

interface ICheckoutService {
    fun scan(product: Product): Double
}