package dsl

class ZeroOrMore(private val group: Group) : Group() {
    
    override fun toString(): String {
        return group.toString() + "*"
    }
}