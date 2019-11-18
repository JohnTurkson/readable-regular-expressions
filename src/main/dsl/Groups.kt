package dsl

import dsl.RepetitionType.*

abstract class Group : Regex() {
    abstract override fun toString(): String
}

class AnchorGroup(private val anchor: Anchor) : Group() {
    override fun toString(): String {
        return anchor.code
    }
}

class CaptureGroup : Group() {
    override fun toString(): String {
        return "(" + groups.joinToString(separator = "") + ")"
    }
}

class NonCaptureGroup : Group() {
    override fun toString(): String {
        return "(?:" + groups.joinToString(separator = "") + ")"
    }
}

class OptionalGroup : Group() {
    override fun toString(): String {
        return groups.joinToString(separator = "", prefix = "(?:", postfix = ")?")
    }
}

class RepeatedGroup(private val rule: RepetitionType, private var min: Int, private var max: Int = min) : Group() {
    override fun toString(): String {
        return when (rule) {
            EXACTLY -> groups.joinToString(separator = "", prefix = "(?:", postfix = "){$min,$max}")
            AT_LEAST -> groups.joinToString(separator = "", prefix = "(?:", postfix = "){$min,}")
            AT_MOST -> groups.joinToString(separator = "", prefix = "(?:", postfix = "){0,$max}")
        }
    }
}

class AtomicGroup : Group() {
    override fun toString(): String {
        return groups.joinToString(separator = "", prefix = "(?>", postfix = ")")
    }
}
