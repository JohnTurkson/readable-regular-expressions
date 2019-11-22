package dsl

class Selection(val negated: Boolean) : Group() {
    
    override fun toString(): String {
        var result = ""
        if (negated) {
            result += "[^"
    
            for (g in groups) {
                result += when (g) {
                    is Character -> g.toString()
                    else -> g.toString().replace("[", "").replace("]", "")
                }
            }
            result += "]"
    
            return result
    
        } else {
            result += "("
    
            for (g in groups) {
                result += when (g) {
                    is Character -> g.toString()
                    else -> g.toString()
                }
                result += "|"
            }
            result = result.dropLast(1)
            result += ")"
    
            return result
        }
        
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
