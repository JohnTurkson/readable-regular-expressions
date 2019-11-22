package dsl

class Escape(val type: EscapeType): Group() {
    override fun toString(): String {
        return type.code
    }
}

enum class EscapeType(val code: String) {
    TAB("\\t"),
    BACKSLASH("\\\\"),
    RETURN("\\r"),
    NEWLINE("\\n")
}
