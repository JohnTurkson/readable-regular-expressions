package dsl

open class Regex {
    internal val children: MutableList<Regex> = mutableListOf()
    
    override fun toString(): String {
        return children.joinToString(separator = "")
    }
    
    fun toRegex(): kotlin.text.Regex {
        return Regex(this.toString())
    }
    
    fun literal(init: () -> String): Regex {
        val literal = Literal(init())
        this.children.add(literal)
        return this
    }
    
    fun oneOf(init: OneOf.() -> Regex): Regex {
        val oneOf = OneOf().init()
        this.children += oneOf
        return this
    }
    
    fun captureGroup(init: CaptureGroup.() -> Regex): Regex {
        val captureGroup = CaptureGroup().init()
        this.children.add(captureGroup)
        return this
    }
    
    fun optional(init: Optional.() -> Regex): Regex {
        val optional = Optional().init()
        this.children.add(optional)
        return this
    }
    
    fun regex(init: Regex.() -> Regex): Regex {
        val regex = Regex().init()
        this.children.add(regex)
        return this
    }
}

fun regex(init: Regex.() -> Regex): Regex {
    return Regex().init()
}
