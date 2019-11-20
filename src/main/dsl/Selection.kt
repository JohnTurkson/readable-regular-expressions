package dsl

class Selection : Group() {
    
    override fun toString(): String {
        var result = ""
    
        for (g in groups) {
            result += when (g) {
                is Character -> g.toString()
                // TODO
                else -> g.toString()
            }
        }
    
        return result
        
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
