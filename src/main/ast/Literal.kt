package ast

import exceptions.RegexSyntaxException

class Literal(val characters : String) : Group("literal") {
    
    override fun render(builder: StringBuilder) {
        if (characters.isEmpty()) {
            throw RegexSyntaxException("Literal should not be empty.")
        } else {
            builder.append(characters)
        }
    }
}