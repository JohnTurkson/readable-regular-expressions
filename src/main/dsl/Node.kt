package dsl

@DslMarker
annotation class NodeMarker

@NodeMarker
abstract class Node {
    abstract fun render(builder: StringBuilder)
    
}