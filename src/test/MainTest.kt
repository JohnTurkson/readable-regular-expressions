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
        assertEquals("a?", regex {
            optional {
                literal { "a" }
            }
        }.toString())
    }
    
    @Test
    fun testZeroOrMore() {
        assertEquals("a*", regex {
            zeroOrMore {
                literal { "a" }
            }
        }.toString())
    }
    
    @Test
    fun testOneOrMore() {
        assertEquals("a+", regex {
            oneOrMore {
                literal { "a" }
            }
        }.toString())
    }
    
    @Test
    fun testanyOf() {
        assertEquals("[1-7]", regex {
            anyOf {
                range {1..7}
            }
        }.toString())
        
        assertEquals("[abc]", regex {
            anyOf {
                "abc"
            }
        }.toString())
    
        assertEquals("[abcD-F]", regex {
            anyOf {
                literal { "a" }
                literal { "b" }
                literal { "c" }
                range{"D".."F"}
            }
        }.toString())
        
        assertEquals("(\"aa\"|\"bb\"|\"cc\")", regex {
            anyOf {
                literal { "aa" }
                literal { "bb" }
                literal { "cc" }
            }
        }.toString())
    }
    
    @Test
    fun testNoneOf() {
        assertEquals("[^a-z]", regex {
            noneOf {
                range { "a".."z" }
            }
        }.toString())
        
        assertEquals("[^abc]", regex {
            noneOf {
                "abc"
            }
        }.toString())
    
        assertEquals("[^D-Fabc]", regex {
            noneOf {
                range{"D".."F"}
                literal { "a" }
                literal { "b" }
                literal { "c" }
            }
        }.toString())
    }

}
