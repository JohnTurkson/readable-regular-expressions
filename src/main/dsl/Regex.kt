package dsl

class Regex : Node() {
    private val expressions = mutableListOf<Node>()
    
    override fun toString(): String {
        // TODO
        var builder = ""
        for (e in expressions) {
            builder += e.toString()
        }
        return builder
    }
    
    fun optional(init: () -> Group): Optional {
        val node = Optional(init())
        expressions.add(node)
        return node
    }
    
    fun zeroOrMore(init: () -> Group): ZeroOrMore {
        val node = ZeroOrMore(init())
        expressions.add(node)
        return node
    }
    
    fun oneOrMore(init: () -> Group): OneOrMore {
        val node = OneOrMore(init())
        expressions.add(node)
        return node
    }

    fun wildcard(): WildCard {
        val node = WildCard()
        expressions.add(node)
        return node
    }
    
    fun anyOf(init: AnyOf.() -> Any): AnyOf {
        var node = AnyOf(mutableListOf())
        val runVal = node.init()
        if(runVal is String) {
            node = AnyOf(mutableListOf(DSLEnum(runVal)))
        } else {
            require(runVal is Terminal)
        }
        expressions.add(node)
        return node
    }
    
    fun noneOf(init: NoneOf.() -> Any): NoneOf {
        var node = NoneOf(mutableListOf())
        val runVal = node.init()
        if(runVal is String) {
            node = NoneOf(mutableListOf(DSLEnum(runVal)))
        } else {
            require(runVal is Terminal)
        }
        expressions.add(node)
        return node
    }
    
    fun range(init: () -> ClosedRange<*>): DSLRange {
        val range = init()
        val r = range.toString().split("\\.\\.".toRegex())
        return DSLRange(r[0], r[1])
    }
    
    fun literal(init: () -> String): Literal {
        return Literal(init())
    }
}

fun regex(init: Regex.() -> Unit): Regex {
    val node = Regex()
    node.init()
    return node
}