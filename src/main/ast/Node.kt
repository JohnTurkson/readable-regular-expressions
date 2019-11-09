package ast

@DslMarker
annotation class RegexNodeMarker


// @RegexNodeMarker
abstract class Node(val name: String) {
    abstract fun render(builder: StringBuilder)
    
}