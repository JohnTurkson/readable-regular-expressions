package dsl

class Anchor(private val anchor: AnchorType) : Group() {
    override fun toString(): String {
        return anchor.code
    }
}

enum class AnchorType(val code: String) {
    START("^"),
    END("$")
}
