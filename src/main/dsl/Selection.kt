package dsl

class Selection : Group() {
    private val positiveCharacters: MutableSet<Char> = sortedSetOf()
    private val negativeCharacters: MutableSet<Char> = sortedSetOf()
    private val positiveRanges: MutableSet<CharRange> = sortedSetOf(compareBy(CharRange::first))
    private val negativeRanges: MutableSet<CharRange> = sortedSetOf(compareBy(CharRange::first))
    
    override fun toString(): String {
        var result = ""
        
        if (positiveCharacters.isNotEmpty()) {
            result += positiveCharacters.joinToString(separator = "", prefix = "[", postfix = "]")
        }
        
        if (negativeCharacters.isNotEmpty()) {
            if (result.isNotEmpty()) result += "|"
            result += negativeCharacters.joinToString(separator = "", prefix = "[^", postfix = "]")
        }
        
        if (positiveRanges.isNotEmpty()) {
            if (result.isNotEmpty()) result += "|"
            result += positiveRanges.joinToString(
                    separator = "",
                    prefix = "[",
                    postfix = "]"
            ) { "${it.first}-${it.last}" }
        }
        
        if (negativeRanges.isNotEmpty()) {
            if (result.isNotEmpty()) result += "|"
            result += negativeRanges.joinToString(
                    separator = "",
                    prefix = "[^",
                    postfix = "]"
            ) { "${it.first}-${it.last}" }
        }
        
        if (groups.isNotEmpty()) {
            if (result.isNotEmpty()) result += "|"
            result += groups.joinToString(separator = "|", prefix = "(?:", postfix = ")")
        }
        
        return "(?:$result)"
    }
    
    fun character(negated: Boolean = false, init: Selection.() -> Char): Selection {
        val character = Selection().init()
        if (negated) negativeCharacters += character else positiveCharacters += character
        return this
    }
    
    fun range(negated: Boolean = false, init: Selection.() -> CharRange): Selection {
        val range = Selection().init()
        if (negated) negativeRanges += range else positiveRanges += range
        return this
    }
}
