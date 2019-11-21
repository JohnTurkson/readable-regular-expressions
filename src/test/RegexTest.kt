import dsl.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class RegexTest {
    
    @Test
    fun testOptional() {
        // Literal cannot contain optional, optional must contain a single group type object cases are covered at compile-time
        regex {
            optional {
                literal { "a" }
            }
            matches { "a" }
            no_match { "b" }
            no_match { "aa" }
        }
    }
    
    @Test
    fun testFailTestMatches() {
        assertFailsWith<AssertionError> {
            regex {
                optional {
                    literal { "a" }
                }
                matches { "abc" }
            }
        }
    }
    
    @Test
    fun testFailTestNoMatche() {
        assertFailsWith<AssertionError> {
            regex {
                optional {
                    literal { "a" }
                }
                no_match { "a" }
            }
        }
    }
    
    @Test
    fun testZeroOrMore() {
        regex {
            repeat(rule = RepetitionType.AT_LEAST, amount = 0) {
                literal { "a" }
            }
            literal { "b" }
            
            matches { "ab" }
            matches { "aaaab" }
            matches { "b" }
            no_match { "c" }
        }
    }
    
    @Test
    fun testOneOrMore() {
        regex {
            repeat(rule = RepetitionType.AT_LEAST, amount = 1) {
                literal { "a" }
            }
            
            matches { "a" }
            matches { "aaaaaa" }
            no_match { "" }
            no_match { "bc" }
        }
    }
    
    @Test
    fun testWildCard() {
        regex {
            metacharacter { MetacharacterType.WILDCARD }
            repeat(rule = RepetitionType.AT_LEAST, amount = 1) {
                literal { "a" }
            }
            repeat(rule = RepetitionType.AT_LEAST, amount = 0) {
                literal { "b" }
            }
            metacharacter { MetacharacterType.WILDCARD }
            
            matches { "zabx" }
            matches { "zaaaaabx" }
            matches { "zax" }
            matches { "zabbbbbx" }
            no_match { "ab" }
            no_match { "ab" }
        }
    }
    
    @Test
    fun testanyOf() {
        regex {
            range { '1'..'7' }
            
            matches { "1" }
            matches { "4" }
            matches { "7" }
            no_match { "8" }
            no_match { "0" }
        }
        
        
        println(regex {
            either {
                literal { "a" }
                literal { "b" }
                literal { "c" }
            }
        })
        regex {
            either {
                literal { "a" }
                literal { "b" }
                literal { "c" }
            }
            
            matches { "a" }
            matches { "b" }
            matches { "c" }
            no_match { "ab" }
            no_match { "bc" }
            no_match { "d" }
        }
        
        regex {
            either {
                literal { "a" }
                literal { "b" }
                range { 'D'..'F' }
            }
            
            matches { "a" }
            matches { "b" }
            matches { "D" }
            matches { "E" }
            matches { "F" }
            no_match { "c" }
            no_match { "C" }
            no_match { "aD" }
        }
        
        regex {
            either {
                literal { "aa" }
                literal { "bb" }
            }
            
            matches { "aa" }
            matches { "bb" }
            no_match { "aabb" }
            no_match { "acac" }
        }
    }
    
    @Test
    fun testNoneOf() {
        // TODO allow this behavior
//        assertEquals("[^a-z]", regex {
//            range(negated = true) { 'a'..'z' }
//        }.toString())
//
//        assertEquals("[^abc]", regex {
//            noneOf {
//                "abc"
//            }
//        }.toString())
//
//        assertEquals("[^D-Fabc]", regex {
//            noneOf {
//                range { 'D'..'F' }
//                literal { "a" }
//                literal { "b" }
//                literal { "c" }
//            }
//        }.toString())
    }
    
    @Test
    fun testWhitespace() {
        regex {
            metacharacter { MetacharacterType.WHITESPACE }
            
            matches { " " }
            matches { "\n" }
            no_match { "'" }
        }
    }
    
    @Test
    fun testMetas() {
        regex {
            metacharacter { MetacharacterType.DIGIT }
            either {
                metacharacter { MetacharacterType.WHITESPACE }
                metacharacter { MetacharacterType.WORD }
            }
            
            matches { "8 " }
            matches { "1c" }
            matches { "1_" }
            matches { "14" }
            no_match { "ad" }
            no_match { "9ad" }
            
        }
    }
    
    
    @Test
    fun testNegatedRange() {
        print(regex {
            range(negated = true) { 'a'..'z' }}.toString())
        regex {
            range(negated = true) { 'a'..'z' }
            
            matches { "1" }
            matches { "!" }
            no_match { "a" }
        }
    }
    
    @Test
    fun testWhatever() {
        regex {
            repeat {
                range { '0'..'9' }
            }
            either {
                literal { " " }
                literal { "\t" }
            }
            
            matches { "1232424\t" }
            matches { "1232424 " }
            no_match { "a1278" }
        }
    }
    
    @Test
    fun testLazy() {
        val exp = regex {
            literal { "'" }
            repeat(behavior = QuantifierBehaviorType.LAZY, rule = RepetitionType.AT_LEAST, amount = 1) {
                metacharacter { MetacharacterType.WORD }
            }
            literal { "'" }
        }
        assertEquals("'one'", exp.find("'one'blah blah'")?.value)
    }
    
    @Test
    fun testAnchors() {
        regex {
            anchor { AnchorType.START }
            literal { "test" }
            anchor { AnchorType.END }
            
            matches { "test" }
            no_match { "atestb" }
        }
    }
    
    @Test
    fun testRepeat() {
        regex {
            repeat(4, 4) {
                literal { "r" }
            }
            
            matches { "rrrr" }
            no_match { "rrr" }
            no_match { "rrrrr" }
        }
    }
}