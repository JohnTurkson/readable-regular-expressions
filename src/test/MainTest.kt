import dsl.regex
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MainTest {
    
    @BeforeEach
    fun setUp() {
        
    }
    
    @AfterEach
    fun tearDown() {
        
    }
    
    @Test
    fun testOptional() {
        // Literal cannot contain optional, optional must contain a single group type object cases are covered at compile-time
        val sb = StringBuilder()
        regex {
            optional {
                literal { "a" }
            }
        }.render(sb)
        assertEquals("a?", sb.toString())
    }
    
    @Test
    fun testZeroOrMore() {
        val sb = StringBuilder()
        regex {
            zeroOrMore {
                literal { "a" }
            }
        }.render(sb)
        assertEquals("a*", sb.toString())
    }
    
    @Test
    fun testOneOrMore() {
        val sb = StringBuilder()
        regex {
            oneOrMore {
                literal { "a" }
            }
        }.render(sb)
        assertEquals("a+", sb.toString())
    }
    
    @Test
    fun testanyOf() {
        var sb = StringBuilder()
        regex {
            anyOf {
                "a..z"
            }
        }.render(sb)
        assertEquals("[a-z]", sb.toString())
    
        sb = StringBuilder()
        regex {
            anyOf {
                "abc"
            }
        }.render(sb)
        assertEquals("[abc]", sb.toString())
    
        sb = StringBuilder()
        regex {
            anyOf {
                literal { "aa" }
                literal { "bb" }
                literal { "cc" }
            }
        }.render(sb)
        assertEquals("(\"aa\"|\"bb\"|\"cc\")", sb.toString())
    }
    
    @Test
    fun testNoneOf() {
        var sb = StringBuilder()
        regex {
            noneOf {
                "a..z"
            }
        }.render(sb)
        assertEquals("[^a-z]", sb.toString())
        
        sb = StringBuilder()
        regex {
            noneOf {
                "abc"
            }
        }.render(sb)
        assertEquals("[^abc]", sb.toString())
        sb = StringBuilder()
        regex {
            noneOf {
                "abc, D..Z"
            }
        }.render(sb)
        assertEquals("[^abcD-Z]", sb.toString())
    }

}
