package dsl

abstract class Group: Regex() {
    abstract override fun toString(): String
}
