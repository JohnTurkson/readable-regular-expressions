package dsl

class Range(val from : String, val to : String) : Terminal() {
    
    override fun render(builder: StringBuilder) {
        builder.append(from)
        builder.append('-')
        builder.append(to)
    }
}