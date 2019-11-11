package dsl

class AnyOf(private val content : ArrayList<Terminal>) : Group() {
    
    constructor(s : String) : this(parseString(s))

//    Literals can be sequences of multiple characters and must be handled differently
    override fun render(builder: StringBuilder) {
        var previousIsLiteral = false
    
        for (e in content) {
            if(e is Literal){
                if(!previousIsLiteral) {
                    builder.append('(')
                } else {
                    builder.append('|')
                }
                builder.append('"')
                
                e.render(builder)
                
                builder.append('"')
                previousIsLiteral = true
            } else {
                if(previousIsLiteral){
                    builder.append(")|[")
                } else if (e == content[0]) {
                    builder.append("[")
                }
                e.render(builder)
            }
            
        }
    
        if(previousIsLiteral){
            builder.append(")")
        } else {
            builder.append("]")
        }
        
    }
    
    
    fun literal(init: () -> String): Literal {
        val lit = Literal(init())
        content.add(lit)
        return lit
    }
    
}

class Enum(val v : String) : Terminal() {
    override fun render(builder: StringBuilder) {
        builder.append(v)
    }
}

fun parseString(s: String) : ArrayList<Terminal> {
    val los = s.split(",")
    val list = arrayListOf<Terminal>()
    
    for (elem in los) {
        if(".." in elem) {
            val r = elem.trim().split("\\.\\.".toRegex())
            list.add(Range(r[0], r[1]))
        } else {
            list.add(Enum(elem.trim()))
        }
    }
    return list
}
