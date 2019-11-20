package dsl

class Lookaround : Group() {
    override fun toString(): String {
        TODO()
    }
}

enum class LookaroundType {
    POSITIVE_LOOKAHEAD,
    NEGATIVE_LOOKAHEAD,
    POSITIVE_LOOKBEHIND,
    NEGATIVE_LOOKBEHIND
}
