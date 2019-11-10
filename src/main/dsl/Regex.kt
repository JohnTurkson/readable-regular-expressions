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
    
    fun literal(init: () -> String): Literal {
        return Literal(init())
    }
}

fun regex(init: Regex.() -> Unit): Regex {
    val node = Regex()
    node.init()
    return node
}