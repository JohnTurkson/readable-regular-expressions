package dsl

class Escape: Group() {
    override fun toString(): String {
        TODO()
    }
}

enum class EscapeType(val code: String) {
    TAB("\t"),
    BACKSLASH("\\"),
    RETURN("\r"),
    NEWLINE("\n")
}
