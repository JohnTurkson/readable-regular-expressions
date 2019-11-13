package dsl

open class AnyOf(private val content: MutableList<Terminal>) : Group() {
    
    // Literals can be sequences of multiple characters and must be handled differently
    override fun toString(): String {
        var previousIsLiteral = false
        var builder = ""
        
        for (e in content) {
            if (e is Literal && e.toString().length > 1) {
                if (!previousIsLiteral) {
                    builder += '('
                } else {
                    builder += '|'
                }
                
                builder += '"'
                builder += e.toString()
                builder += '"'
                previousIsLiteral = true
            } else {
                if (previousIsLiteral) {
                    builder += ")|["
                } else if (e == content[0]) {
                    builder += "["
                }
                builder += e.toString()
            }
        }
        
        if (previousIsLiteral) {
            builder += ")"
        } else {
            builder += "]"
        }
        return builder
    }
    
    fun literal(init: () -> String): Literal {
        val lit = Literal(init())
        content.add(lit)
        return lit
    }
    
    fun range(init: () -> Any): DSLRange {
        val range = init()
        val lst = range.toString().split("\\.\\.".toRegex())
        val r = DSLRange(lst[0], lst[1])
        content.add(r)
        return r
    }
    
}

class DSLEnum(val v: String) : Terminal() {
    override fun toString(): String {
        return v
    }
}

