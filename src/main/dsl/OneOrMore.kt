package dsl

class OneOrMore(private var group: Group) : Group() {
    
    override fun toString(): String {
        return group.toString() + "+"
    }
    
    fun literal(init: () -> String): Literal {
        val literal = Literal(init())
        group = literal
        return literal
    }
}
