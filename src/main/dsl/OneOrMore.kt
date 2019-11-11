package dsl

class OneOrMore(private val group: Group) : Group() {
    
    override fun toString(): String {
        return group.toString() + "+"
    }
}