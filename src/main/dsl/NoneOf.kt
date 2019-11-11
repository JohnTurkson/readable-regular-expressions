package dsl

import exceptions.RegexSyntaxException

class NoneOf(private val content : MutableList<Terminal>) : AnyOf(content) {
    
    override fun toString(): String {
        for (e in content) {
            if (e is Literal && e.toString().length > 1) {
                throw RegexSyntaxException("noneOf does not accept literals of length > 1.")
            }
        }
        return super.toString().replace("[", "[^")
    }
}