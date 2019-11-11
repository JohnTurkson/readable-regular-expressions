package dsl

class Literal(private val characters : String) : Terminal() {
    
    override fun toString(): String {
        require (!characters.isEmpty()) {"Literal should not be empty."}
        return characters
    }
}