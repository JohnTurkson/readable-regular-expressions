package dsl

open class Regex {
    internal val children: MutableList<Regex> = mutableListOf()
    internal val modifiers: MutableMap<Regex, Modifiers> = mutableMapOf()
    internal var previousModifiers: Modifiers = Modifiers()
    
    data class Modifiers(internal val enabled: MutableList<Flag> = mutableListOf(),
                         internal val disabled: MutableList<Flag> = mutableListOf()) {
        
        fun enable(init: Modifiers.() -> Flag): Modifiers {
            enabled += init()
            return this
        }
        
        fun disable(init: Modifiers.() -> Flag): Modifiers {
            disabled += init()
            return this
        }
        
        override fun toString(): String {
            return if (enabled.isEmpty() && disabled.isEmpty()) ""
            else "(?" +
                    enabled.joinToString(separator = "") { it.code } +
                    disabled.joinToString(separator = "") { "-" + it.code } +
                    ")"
        }
    }
    
    override fun toString(): String {
        // var result = ""
        // for (i in 0 until children.size) {
        //     if (modifiers[i].enabled.isNotEmpty() || modifiers[i].disabled.isNotEmpty()) {
        //         result += "(?" +
        //                 modifiers[i].enabled.joinToString(separator = "") {it.code} +
        //                 modifiers[i].disabled.joinToString(separator = "") {"-" + it.code} +
        //                 ")"
        //     }
        //     result += children[i]
        // }
        // return result
        
        // println(children.filter { it.toString().isNotBlank() }.count())
        return "<" + children.filter { it.toString().isNotBlank() }.joinToString(separator = "") + ">"
    }
    
    fun toRegex(): kotlin.text.Regex {
        return Regex(this.toString())
    }
    
    open fun flags(init: Modifiers.() -> Modifiers) {
        // val copy = Modifiers()
        // previousModifiers.enabled.forEach { copy.enable { it } }
        // previousModifiers.disabled.forEach { copy.disable { it } }
        //
        // val modifier = copy.init()
        // previousModifiers = modifier
        val blank = Regex()
        children.add(blank)
        modifiers[blank] = Modifiers().init()
    }
    
    fun literal(init: Literal.() -> String): Regex {
        // val copy = Modifiers()
        // previousModifiers.enabled.forEach { copy.enable { it } }
        // previousModifiers.disabled.forEach { copy.disable { it } }
        //
        // val literal = Literal(init())
        // children.add(literal)
        // modifiers.add(previousModifiers)
        //
        // previousModifiers = copy
        // return this
        
        val literal = Literal(Literal().init())
        children.add(literal)
        return this
    }
    
    // fun oneOf(init: OneOf.() -> Regex): Regex {
    //     val oneOf = OneOf().init()
    //     this.children += oneOf
    //     return this
    // }
    //
    // fun captureGroup(init: CaptureGroup.() -> Regex): Regex {
    //     val captureGroup = CaptureGroup().init()
    //     this.children.add(captureGroup)
    //     return this
    // }
    //
    // fun optional(init: Optional.() -> Regex): Regex {
    //     val optional = Optional().init()
    //     this.children.add(optional)
    //     return this
    // }
    
    fun regex(init: Regex.() -> Regex): Regex {
        // val copy = Modifiers()
        // previousModifiers.enabled.forEach { copy.enable { it } }
        // previousModifiers.disabled.forEach { copy.disable { it } }
        
        val regex = Regex().init()
        children.add(regex)
        modifiers[regex] = Modifiers()
        
        // copy.enabled.forEach { c -> regex.modifiers.forEach { modifier -> modifier.enable { c } } }
        // copy.disabled.forEach { c -> regex.modifiers.forEach { modifier -> modifier.disable { c } } }
        
        // copy.enabled.forEach { regex.previousModifiers.enable { it } }
        // copy.disabled.forEach { regex.previousModifiers.disable { it } }
        
        // this.modifiers.add(regex.previousModifiers)
        
        return this
    }
}

fun regex(init: Regex.() -> Regex): Regex {
    return Regex().init()
}
