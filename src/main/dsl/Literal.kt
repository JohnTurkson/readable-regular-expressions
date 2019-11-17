package dsl

class Literal(private val value: String = "") : Regex() {
    override fun flags(init: Modifiers.() -> Modifiers) {
        // val copy = Modifiers()
        // previousModifiers.enabled.forEach { copy.enable { it } }
        // previousModifiers.disabled.forEach { copy.disable { it } }
        //
        // val modifier = copy.init()
        // previousModifiers = modifier
        
        println(modifiers.values)
        val modifier = Modifiers().init()
        if (modifiers.containsKey(this)) {
            modifiers[this]!!.enabled.addAll(modifier.enabled)
            modifiers[this]!!.disabled.addAll(modifier.disabled)
        } else {
            modifiers[this] = Modifiers().init()
        }
        println(modifiers.values)
    }
    
    override fun toString(): String {
        if (modifiers.containsKey(this)) {
            return "(?:" + modifiers[this] + value + ")"
        } else {
            return value
        }
    }
}
