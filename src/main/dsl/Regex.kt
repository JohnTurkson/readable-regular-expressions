package dsl

import dsl.QuantifierBehaviorType.*
import dsl.RepetitionType.*


open class Regex {
    internal val groups: MutableList<Regex> = mutableListOf()
    internal val tests: MutableList<TestCase> = mutableListOf()
    
    override fun toString(): String {
        return groups.joinToString(separator = "")
    }
    
    fun literal(init: Literal.() -> String): Regex {
        val literal = Literal(Literal("").init())
        groups += literal
        return this
    }
    
    fun character(negated: Boolean = false, init: Selection.() -> Char): Regex {
        val character = Character(Selection().init(), negated)
        groups += character
        return this
    }
    
    fun anchor(init: () -> AnchorType): Regex {
        val anchor = init()
        groups += Anchor(anchor)
        return this
    }
    
    fun range(negated: Boolean = false, init: Selection.() -> CharRange): Regex {
        val range = Range(Selection().init(), negated)
        groups += range
        return this
    }
    
    fun metacharacter(negated: Boolean = false, init: Selection.() -> MetacharacterType): Regex {
        val metacharacter = Metacharacter(Selection().init(), negated)
        groups += metacharacter
        return this
    }
    
    fun capture(init: CaptureGroup.() -> Regex): Regex {
        val captureGroup = CaptureGroup().init()
        groups += captureGroup
        return this
    }
    
    fun optional(behavior: QuantifierBehaviorType = GREEDY, init: OptionalGroup.() -> Regex): Regex {
        val optional = OptionalGroup(behavior).init()
        groups += optional
        return this
    }
    
    fun repeat(behavior: QuantifierBehaviorType = GREEDY, init: RepeatedGroup.() -> Regex): Regex {
        val repeat = RepeatedGroup(AT_LEAST, 1, behavior = behavior).init()
        groups += repeat
        return this
    }
    
    fun repeat(min: Int, max: Int, behavior: QuantifierBehaviorType = GREEDY, init: RepeatedGroup.() -> Regex): Regex {
        require(min >= 0)
        require(max >= 0)
        require(max >= min)
        
        val repeat = RepeatedGroup(EXACTLY, min, max, behavior).init()
        groups += repeat
        return this
    }
    
    fun repeat(range: IntRange, behavior: QuantifierBehaviorType = GREEDY, init: RepeatedGroup.() -> Regex): Regex {
        require(range.first >= 0)
        require(range.last >= 0)
        require(range.last >= range.first)
        
        val repeat = RepeatedGroup(EXACTLY, range.first, range.last, behavior).init()
        groups += repeat
        return this
    }
    
    fun repeat(rule: RepetitionType, amount: Int, behavior: QuantifierBehaviorType = GREEDY, init: RepeatedGroup.() -> Regex): Regex {
        require(amount >= 0)
        
        val repeat = RepeatedGroup(rule, amount, amount, behavior).init()
        groups += repeat
        return this
    }
    
    fun either(init: Selection.() -> Regex): Regex {
        val selection = Selection().init()
        groups += selection
        return this
    }
    
    fun atomic(init: AtomicGroup.() -> Regex): Regex {
        val atomic = AtomicGroup().init()
        groups += atomic
        return this
    }
    
    fun assertAhead() {
        
    }
    
    fun assertNotAhead() {
        
    }
    
    fun assertBehind() {
        
    }
    
    fun assertNotBehind() {
        
    }
    
    fun regex(init: Regex.() -> Regex): Regex {
        val regex = Regex().init()
        groups += regex
        return this
    }
    
    // TODO enforce grouping
    fun matches(init: Match.() -> String): Regex {
        val t = Match(Match("").init())
        tests += t
        return this
    }
    
    fun no_match(init: NoMatch.() -> String): Regex {
        val noMatch = NoMatch(NoMatch("").init())
        tests += noMatch
        return this
    }
    
    fun runTests(exp : kotlin.text.Regex) {
        for (test in tests) {
            test.run(exp)
        }
    }
}

fun regex(init: Regex.() -> Regex): kotlin.text.Regex {
    val regex = Regex().init()
    regex.runTests(Regex(regex.toString()))
    return Regex(regex.toString())
}
