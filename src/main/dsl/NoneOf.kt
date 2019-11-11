package dsl

class NoneOf(private val content : ArrayList<Terminal>) : Group() {
    constructor(s : String) : this(parseString(s))
    
    override fun render(builder: StringBuilder) {
        builder.append("[^")
        for (e in content) {
            e.render(builder)
        }
        builder.append(']')
    }
}