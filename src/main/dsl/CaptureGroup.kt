package dsl

class CaptureGroup : Group() {
    override fun toString(): String {
        return "(" + groups.joinToString(separator = "") {
            if (modifiers.contains(it)) {
                "${modifiers[it].toString()}$it"
            } else {
                "$it"
            }
        } + ")"
    }
}
