package dsl

class Literal(private val characters : String) : Terminal() {
    
    override fun render(builder: StringBuilder) {
        require (!characters.isEmpty()) {"Literal should not be empty."}
        builder.append(characters)
    }
}