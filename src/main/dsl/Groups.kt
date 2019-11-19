package dsl

import dsl.QuantifierBehaviorType.*
import dsl.RepetitionType.*

abstract class Group : Regex() {
    abstract override fun toString(): String
}

class Range(private val range: CharRange, private val negated: Boolean) : Group() {
    override fun toString(): String {
        return if (negated) {
            "[^${range.first}-${range.last}]"
        } else {
            "${range.first}-${range.last}"
        }
    }
}

class CaptureGroup : Group() {
    override fun toString(): String {
        return "(" + groups.joinToString(separator = "") + ")"
    }
}

abstract class Quantifier(val behavior: QuantifierBehaviorType) : Group()

class OptionalGroup(behavior: QuantifierBehaviorType) : Quantifier(behavior) {
    override fun toString(): String {
        val result = groups.joinToString(separator = "", prefix = "(?:", postfix = ")?")
        
        return when (behavior) {
            GREEDY -> result
            LAZY -> "$result?"
            POSSESSIVE -> "$result+"
        }
    }
}

class RepeatedGroup(
        private val rule: RepetitionType,
        private val min: Int,
        private val max: Int = min,
        behavior: QuantifierBehaviorType
) : Quantifier(behavior) {
    override fun toString(): String {
        var result = groups.joinToString(separator = "", prefix = "(?:", postfix = ")")
        
        result = when (rule) {
            EXACTLY -> "$result{$min,$max}"
            AT_LEAST -> "$result{$min,}"
            AT_MOST -> "$result{0,$max}"
        }
        
        result = when (behavior) {
            GREEDY -> result
            LAZY -> "$result?"
            POSSESSIVE -> "$result+"
        }
        
        return result
    }
}

class AtomicGroup : Group() {
    override fun toString(): String {
        return groups.joinToString(separator = "", prefix = "(?>", postfix = ")")
    }
}
