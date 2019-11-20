package dsl

class Literal(private val text: String) : Group() {
    override fun toString(): String {
        return text
    }
}

class Character(private val character: Char, private val negated: Boolean) : Group() {
    override fun toString(): String {
        return if (negated) {
            "[^$character]"
        } else {
            character.toString()
        }
    }
}
