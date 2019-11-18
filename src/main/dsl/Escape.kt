package dsl

enum class Escape(val code: String) {
    TAB("\t"),
    BACKSLASH("\\"),
    RETURN("\r"),
    NEWLINE("\n")
}
