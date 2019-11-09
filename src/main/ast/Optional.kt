package ast

import exceptions.RegexSyntaxException

class Optional(val group: Group) : Group("optional") {
    // val group = arrayListOf<Group>()
    
    override fun render(builder: StringBuilder){
        // if (group.size != 1){
        //     throw RegexSyntaxException("Optional should contain exactly 1 expression.")
        // } else {
            group.render(builder)
            builder.append("?")
        // }
        
    }
}
