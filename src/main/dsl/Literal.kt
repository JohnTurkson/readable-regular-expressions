package dsl

import exceptions.RegexSyntaxException

class Literal(private val characters : String) : Group() {
    
    override fun render(builder: StringBuilder) {
        if (characters.isEmpty()) {
            throw RegexSyntaxException("Literal should not be empty.")
        } else {
            builder.append(characters)
        }
    }
}