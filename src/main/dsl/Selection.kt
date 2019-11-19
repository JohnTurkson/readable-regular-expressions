package dsl

class Selection : Group() {
    
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
        
        return ""
        
        // var result = ""
        //
        // if (positiveSelections.isNotEmpty()) {
        //     result += positiveSelections.joinToString(separator = "", prefix = "[", postfix = "]")
        // }
        //
        // if (negativeSelections.isNotEmpty()) {
        //     if (result.isNotEmpty()) result += "|"
        //     result += negativeSelections.joinToString(separator = "", prefix = "[", postfix = "]")
        // }
        //
        // if (groups.isNotEmpty()) {
        //     if (result.isNotEmpty()) result += "|"
        //     result += groups.joinToString(separator = "|", prefix = "(?:", postfix = ")")
        // }
        //
        // if (result.isNotEmpty()) {
        //     result = "(?:$result)"
        // }
        //
        // return result
    }
}
