package dsl

abstract class TestCase {
    abstract fun run(exp : kotlin.text.Regex)
}

class Match(private val text: String) : TestCase() {
    override fun run(exp : kotlin.text.Regex) {
        if (!exp.matches(text)) {
            throw AssertionError("Failed to match: $text")
        }
    }
}

class NoMatch(private val text: String) : TestCase() {
    override fun run(exp : kotlin.text.Regex) {
        if (exp.matches(text)) {
            throw AssertionError("Unexpected match: $text")
        }
    }
}