package dsl

abstract class Node {
    abstract fun render(builder: StringBuilder)
    
}