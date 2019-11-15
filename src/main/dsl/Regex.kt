package dsl

open class Regex {
    private val modifiers: MutableList<Flag> = mutableListOf()
    internal val children: MutableList<Regex> = mutableListOf()
    
    class Modifiers {
        internal val flags: MutableList<Flag> = mutableListOf()
        
        fun flag(init: Modifiers.() -> Flag): Modifiers {
            flags.add(init())
            return this
        }
    }
    
    override fun toString(): String {
        val regex = StringBuilder()
        
        if (modifiers.isNotEmpty()) {
            regex.append(modifiers.joinToString(separator = "", prefix = "(?", postfix = ")"))
        }
        
        regex.append(children.joinToString(separator = ""))
        
        return regex.toString()
    }
    
    fun toRegex(): kotlin.text.Regex {
        return Regex(this.toString())
    }
    
    fun modifiers(init: Modifiers.() -> Modifiers) {
        modifiers += Modifiers().init().flags
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
