package dsl

class Metacharacter(val type: MetacharacterType, val negated: Boolean): Group() {
    override fun toString(): String {
        return if (negated) {
            "[^${type.code}]"
        } else {
            type.code
        }
    }
}

enum class MetacharacterType(val code: String) {
    WILDCARD("."),
    DIGIT("\\d"),
    NONDIGIT("\\D"),
    WHITESPACE("\\s"),
    NONWHITESPACE("\\S"),
    WORD("\\w"),
    NONWORD("\\W")
}
