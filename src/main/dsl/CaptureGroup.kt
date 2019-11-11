package dsl

class CaptureGroup : Group() {
    override fun toString(): String {
        return children.joinToString(separator = "|", prefix = "(", postfix = ")")
    }
}
