package dsl

class Optional : Group() {
    override fun toString(): String {
        return children.joinToString(separator = "", postfix = "?")
    }
}
