package dsl

import dsl.RepetitionType.*

open class Regex {
    internal val groups: MutableList<Regex> = mutableListOf()
    
    override fun toString(): String {
        return groups.joinToString(separator = "")
    }
    
    fun toRegex(): kotlin.text.Regex {
        return Regex(this.toString())
    }
    
    fun literal(init: Literal.() -> String): Regex {
        val literal = Literal()
        literal.text = literal.init()
        groups += literal
        return this
    }
    
    fun anchor(init: () -> Anchor): Regex {
        val anchor = init()
        groups += AnchorGroup(anchor)
        return this
    }
    
    fun captureGroup(init: CaptureGroup.() -> Regex): Regex {
        val captureGroup = CaptureGroup().init()
        groups += captureGroup
        return this
    }
    
    fun nonCaptureGroup(init: NonCaptureGroup.() -> Regex): Regex {
        val nonCaptureGroup = NonCaptureGroup().init()
        groups += nonCaptureGroup
        return this
    }
    
    fun optional(init: OptionalGroup.() -> Regex): Regex {
        val optional = OptionalGroup().init()
        groups += optional
        return this
    }
    
    fun repeat(init: RepeatedGroup.() -> Regex): Regex {
        val repeat = RepeatedGroup(AT_LEAST, 1).init()
        groups += repeat
        return this
    }
    
    fun repeat(min: Int, max: Int, init: RepeatedGroup.() -> Regex): Regex {
        require(min >= 0)
        require(max >= 0)
        require(max >= min)
        
        val repeat = RepeatedGroup(EXACTLY, min, max).init()
        groups += repeat
        return this
    }
    
    fun repeat(range: IntRange, init: RepeatedGroup.() -> Regex): Regex {
        require(range.first >= 0)
        require(range.last >= 0)
        require(range.last >= range.first)
        
        val repeat = RepeatedGroup(EXACTLY, range.first, range.last).init()
        groups += repeat
        return this
    }
    
    fun repeat(rule: RepetitionType, amount: Int, init: RepeatedGroup.() -> Regex): Regex {
        require(amount >= 0)
        
        val repeat = RepeatedGroup(rule, amount, amount).init()
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
    
    // fun lazy(init: LazyGroup.() -> Regex): Regex {
    //
    // }
    //
    // fun possessive(init: GreedyGroup.() -> Regex): Regex {
    //
    // }
    
    fun regex(init: Regex.() -> Regex): Regex {
        val regex = Regex().init()
        groups += regex
        return this
    }
}

fun regex(init: Regex.() -> Regex): kotlin.text.Regex {
    return Regex().init().toRegex()
}
