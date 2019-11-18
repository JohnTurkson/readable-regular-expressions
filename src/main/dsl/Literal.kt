package dsl

class Literal(internal var text: String = "") : Group() {
    override fun toString(): String {
        return text
    }
}
