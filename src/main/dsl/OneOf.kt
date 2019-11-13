package dsl

class OneOf(private val content : MutableList<Terminal>) : Node() {
    override fun toString(): String {
        return content.joinToString(separator = "|", prefix = "(?:", postfix = ")")
    }
    
    // Only literals supported for now
    fun literal(init: () -> String): Literal {
        val literal = Literal(init())
        content.add(literal)
        return literal
    }
}
