package dsl

class OneOf : Group() {
    override fun toString(): String {
        return children.joinToString(separator = "|", prefix = "(?:", postfix = ")")
    }
}
