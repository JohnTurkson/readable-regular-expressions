package dsl

class Selection : Group() {
    
    override fun toString(): String {
        for (g in groups) {
            when (g) {
                is Character -> g.toString()
                
                // TODO
            }
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
