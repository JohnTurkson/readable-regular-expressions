package dsl

class ZeroOrMore(private val group: Group) : Group() {
    
    override fun render(builder: StringBuilder) {
        group.render(builder)
        builder.append("*")
    }
}