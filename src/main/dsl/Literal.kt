package dsl

class Literal(internal var value: String = ""): Group() {
    private val flags = Modifiers()
    
    override fun flags(init: Modifiers.() -> Modifiers) {
        flags.enabled += Modifiers().init().enabled
        flags.disabled += Modifiers().init().disabled
    }
    
    override fun toString(): String {
        return when {
            flags.isEmpty() -> value
            else -> "$flags$value"
        }
    }
}
