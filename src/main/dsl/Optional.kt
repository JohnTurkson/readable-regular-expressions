package dsl


class Optional(private val group: Group) : Group() {
    
    override fun toString(): String {
        return group.toString() + "?"
    }
}
