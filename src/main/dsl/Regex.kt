package dsl

class Regex : Node() {
    val expressions = arrayListOf<Node>()
    
    override fun render(builder: StringBuilder) {
        // TODO
        for (e in expressions) {
            e.render(builder)
        }
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
    
    fun anyOf(init: AnyOf.() -> Any): AnyOf {
        var node = AnyOf(arrayListOf())
        val runVal = node.init()
        if(runVal is String) {
            node = AnyOf(runVal)
        } else {
            require(runVal is Terminal)
        }
        expressions.add(node)
        return node
    }
    
    fun noneOf(init: () -> String): NoneOf {
        val node = NoneOf(init())
        expressions.add(node)
        return node
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