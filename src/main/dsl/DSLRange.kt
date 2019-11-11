package dsl

class DSLRange(private val from : String, private val to : String) : Terminal() {
    
    override fun toString(): String {
        return from + "-" + to
    }
}