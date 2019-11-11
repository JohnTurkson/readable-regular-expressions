package dsl

class Literal(private val value: String) : Group() {
    override fun toString(): String {
        return value
    }
}
