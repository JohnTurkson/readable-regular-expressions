package dsl

class Optional : Group() {
    override fun toString(): String {
        // return ""
        return groups.joinToString(separator = "", prefix = "(?:", postfix = ")?")
    }
}
