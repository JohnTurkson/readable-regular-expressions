package dsl

class OneOf : Group() {
    override fun toString(): String {
        // return ""
        return groups.joinToString(separator = "|", prefix = "(?:", postfix = ")")
    }
}
