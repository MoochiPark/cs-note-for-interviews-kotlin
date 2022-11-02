package ch01


interface PaymentStrategy {
    fun pay(amount: Int)
}

class KakaoCardStrategy(
    private val name: String,
    private val cardNumber: String,
    private val cvv: String,
    private val ExpireDate: String,
) : PaymentStrategy {
    override fun pay(amount: Int) {
        println("$amount paid using KakaoCard")
    }
}

class LunaCardStrategy(
    private val email: String,
    private val password: String,
) : PaymentStrategy {
    override fun pay(amount: Int) {
        println("$amount paid using LunaCard")
    }
}

data class Item(
    val name: String,
    val price: Int,
)

class ShoppingCart(
    private val items: MutableList<Item> = mutableListOf(),
) {
    fun add(vararg item: Item) = this.items.addAll(item)
    fun remove(item: Item) = this.items.remove(item)
    fun pay(paymentStrategy: PaymentStrategy) =
        paymentStrategy.pay(calculateTotal())

    private fun calculateTotal() = items.sumOf { it.price }
}

fun main() {
    val cart = ShoppingCart()
    val itemA = Item("pie", 3000)
    val itemB = Item("pocky", 1500)
    cart.add(itemA, itemB)

    cart.pay(LunaCardStrategy("daewon@wisoft.io", "1234"))
    cart.pay(KakaoCardStrategy("DAEWON PARK", "12345678", "111", "23/01"))
}