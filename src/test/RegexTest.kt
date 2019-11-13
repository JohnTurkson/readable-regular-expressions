import dsl.Flags.CASE_INSENSITIVE
import dsl.regex
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RegexTest {
    
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
    fun testOptionalNested() {
        // Literal cannot contain optional, optional must contain a single group type object cases are covered at compile-time
        assertEquals("a?", regex {
            optional {
                optional {
                    literal { "a" }
                }
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
    fun testWildCard() {
        assertEquals(".a+b*.", regex {
            wildcard()
            oneOrMore {
                literal { "a" }
            }
            zeroOrMore {
                literal { "b" }
            }
            wildcard()
        }.toString())
    }
    
    @Test
    fun testRepeatExact() {
        assertEquals("a{5}", regex {
            repeat(5) {
                literal { "a" }
            }
        }.toString())
    }
    
    @Test
    fun testRepeatMinMax() {
        assertEquals("a{5,7}", regex {
            repeat(5,7) {
                literal { "a" }
            }
        }.toString())
    }
    
    @Test
    fun testanyOf() {
        assertEquals("[1-7]", regex {
            anyOf {
                range { 1..7 }
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
                range { "D".."F" }
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
                range { "D".."F" }
                literal { "a" }
                literal { "b" }
                literal { "c" }
            }
        }.toString())
    }
    
    @Test
    fun testFlagCaseInsensitive() {
        assertEquals("(?i)A", regex {
            flags {
                CASE_INSENSITIVE
            }
            literal { "A" }
        }.toString())
        
        assertEquals("(?i)a", regex {
            flags {
                CASE_INSENSITIVE
            }
            literal { "a" }
        }.toString())
    }
    
    @Test
    fun testFlagCaseInsensitiveNested() {
        assertEquals("(?i)[^D-Fabc]", regex {
            flags {
                CASE_INSENSITIVE
            }
            noneOf {
                range { "D".."F" }
                literal { "a" }
                literal { "b" }
                literal { "c" }
            }
        }.toString())
    }
    
    @Test
    fun testOneOf() {
        assertEquals("(?:a|b|c)", regex {
            oneOf {
                literal { "a" }
                literal { "b" }
                literal { "c" }
            }
        }.toString())
    }
}
